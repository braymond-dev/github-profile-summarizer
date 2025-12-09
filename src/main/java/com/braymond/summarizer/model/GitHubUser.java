package com.braymond.summarizer.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GitHubUser(
        String login,
        long id,
        String nodeId,
        String avatarUrl,
        String gravatarId,
        String url,
        String htmlUrl,
        String followersUrl,
        String followingUrl,
        String gistsUrl,
        String starredUrl,
        String subscriptionsUrl,
        String organizationsUrl,
        String reposUrl,
        String eventsUrl,
        String receivedEventsUrl,
        String type,
        String userViewType,
        boolean siteAdmin,
        String name,
        String company,
        String blog,
        String location,
        String email,
        Boolean hireable,
        String bio,
        String twitterUsername,
        int publicRepos,
        int publicGists,
        int followers,
        int following,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
