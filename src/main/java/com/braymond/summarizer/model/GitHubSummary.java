package com.braymond.summarizer.model;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GitHubSummary(
        String userName,
        String displayName,
        String avatar,
        String geoLocation,
        String email,
        String url,
        String createdAt,
        List<Repo> repos) {

    public static GitHubSummary from(GitHubUser user, List<GitHubRepo> repos) {
        var repoSummaries = repos.stream()
                .map(repo -> new Repo(repo.name(), repo.htmlUrl()))
                .toList();

        return new GitHubSummary(
                user.login(),
                user.name(),
                user.avatarUrl(),
                user.location(),
                user.email(),
                user.htmlUrl(),
                user.createdAt() != null ? user.createdAt().toString() : null,
                repoSummaries);
    }

    public record Repo(String name, String url) {
    }
}
