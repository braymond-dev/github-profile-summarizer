package com.braymond.summarizer.service;

import com.braymond.summarizer.client.GitHubClient;
import com.braymond.summarizer.model.GitHubRepo;
import com.braymond.summarizer.model.GitHubSummary;
import com.braymond.summarizer.model.GitHubUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SummarizerService {

    private final GitHubClient githubClient;

    public SummarizerService(GitHubClient githubClient) {
        this.githubClient = githubClient;
    }

    public GitHubSummary summarizeProfile(String username) throws Exception
    {

        CompletableFuture<GitHubUser> user = githubClient.getUser(username);
        CompletableFuture<List<GitHubRepo>> repos = githubClient.getUserRepos(username);


        CompletableFuture<GitHubSummary> summaryFuture = user.thenCombine(repos,

                (completeUser, completeRepos) -> {
                    return GitHubSummary.from(completeUser, completeRepos);
                }
        );

        return summaryFuture.get();
    }

    /**
     * Synchronous version of profile summarization, useful for debugging JSON decoding without async threads.
     */
    public GitHubSummary summarizeProfileSync(String username) throws Exception {
        GitHubUser user = githubClient.getUser(username).get();
        List<GitHubRepo> repos = githubClient.getUserRepos(username).get();
        return GitHubSummary.from(user, repos);
    }
}
