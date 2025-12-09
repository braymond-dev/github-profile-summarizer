package com.braymond.summarizer.service;

import com.braymond.summarizer.client.GitHubClient;
import com.braymond.summarizer.model.GitHubRepo;
import com.braymond.summarizer.model.GitHubSummary;
import com.braymond.summarizer.model.GitHubUser;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SummarizerService {

    private final GitHubClient githubClient;
    private final ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private final Duration cacheTtl = Duration.ofMinutes(5);

    public SummarizerService(GitHubClient githubClient) {
        this.githubClient = githubClient;
    }

    public GitHubSummary summarizeProfile(String username) throws Exception
    {
        GitHubSummary cached = getCached(username);
        if (cached != null) {
            return cached;
        }

        CompletableFuture<GitHubUser> user = githubClient.getUser(username);
        CompletableFuture<List<GitHubRepo>> repos = githubClient.getUserRepos(username);

        CompletableFuture<GitHubSummary> summaryFuture = user.thenCombine(repos,
                (completeUser, completeRepos) -> {
                    return GitHubSummary.from(completeUser, completeRepos);
                }
        );
        GitHubSummary summary = summaryFuture.get();
        cache.put(username, new CacheEntry(summary, Instant.now()));
        return summary;
    }

    /**
     * Synchronous version for debugging
     */
    public GitHubSummary summarizeProfileSync(String username) throws Exception {
        GitHubSummary cached = getCached(username);
        if (cached != null) {
            return cached;
        }

        GitHubUser user = githubClient.getUser(username).get();
        List<GitHubRepo> repos = githubClient.getUserRepos(username).get();
        GitHubSummary summary = GitHubSummary.from(user, repos);
        cache.put(username, new CacheEntry(summary, Instant.now()));
        return summary;
    }

    // Simple app uses in-memory cache
    private GitHubSummary getCached(String username) {
        CacheEntry entry = cache.get(username);
        if (entry == null) {
            return null;
        }
        if (entry.cachedAt().plus(cacheTtl).isBefore(Instant.now())) {
            cache.remove(username);
            return null;
        }
        return entry.summary();
    }

    private record CacheEntry(GitHubSummary summary, Instant cachedAt) {
    }
}
