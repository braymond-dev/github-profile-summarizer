package com.braymond.summarizer.service;

import com.braymond.summarizer.client.GitHubClient;
import com.braymond.summarizer.model.GitHubRepo;
import com.braymond.summarizer.model.GitHubSummary;
import com.braymond.summarizer.model.GitHubUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SummarizerServiceTest {

    @Mock
    private GitHubClient githubClient;

    private SummarizerService summarizerService;

    @BeforeEach
    void setUp() {
        summarizerService = new SummarizerService(githubClient);
    }

    @Test
    @DisplayName("combines user and repo data into summary")
    void summarizesProfile() throws Exception {
        GitHubUser user = new GitHubUser(
                "octocat",
                "https://avatars.githubusercontent.com/u/583231?v=4",
                "https://github.com/octocat",
                "The Octocat",
                "San Francisco",
                null,
                OffsetDateTime.parse("2011-01-25T18:44:36Z")
        );
        GitHubRepo repo = new GitHubRepo(
                "Hello-World",
                "https://github.com/octocat/Hello-World"
        );

        when(githubClient.getUser("octocat")).thenReturn(CompletableFuture.completedFuture(user));
        when(githubClient.getUserRepos("octocat")).thenReturn(CompletableFuture.completedFuture(List.of(repo)));

        GitHubSummary summary = summarizerService.summarizeProfile("octocat");

        assertEquals("octocat", summary.userName());
        assertEquals("The Octocat", summary.displayName());
        assertEquals("Hello-World", summary.repos().get(0).name());
        verify(githubClient).getUser("octocat");
        verify(githubClient).getUserRepos("octocat");
    }

    @Test
    @DisplayName("returns cached summary on repeated calls")
    void usesCacheForSameUser() throws Exception {
        GitHubUser user = new GitHubUser(
                "octocat",
                "https://avatars.githubusercontent.com/u/583231?v=4",
                "https://github.com/octocat",
                "The Octocat",
                "San Francisco",
                null,
                OffsetDateTime.parse("2011-01-25T18:44:36Z")
        );
        GitHubRepo repo = new GitHubRepo(
                "Hello-World",
                "https://github.com/octocat/Hello-World"
        );

        when(githubClient.getUser("octocat")).thenReturn(CompletableFuture.completedFuture(user));
        when(githubClient.getUserRepos("octocat")).thenReturn(CompletableFuture.completedFuture(List.of(repo)));

        GitHubSummary first = summarizerService.summarizeProfile("octocat");
        assertEquals("octocat", first.userName());

        // if cache is working, second call should not hit githubClient.
        clearInvocations(githubClient);

        GitHubSummary second = summarizerService.summarizeProfile("octocat");
        assertEquals("octocat", second.userName());
        verifyNoInteractions(githubClient);
    }

    @Test
    @DisplayName("propagates exception when user fetch fails")
    void throwsWhenUserFutureFails() {
        when(githubClient.getUser("bad")).thenReturn(CompletableFuture.failedFuture(new RuntimeException("boom")));
        when(githubClient.getUserRepos("bad")).thenReturn(CompletableFuture.completedFuture(List.of()));

        Exception ex = Assertions.assertThrows(Exception.class, () -> summarizerService.summarizeProfile("bad"));
        // verify the root cause is the same failure we injected
        assertInstanceOf(RuntimeException.class, ex.getCause());
    }
}
