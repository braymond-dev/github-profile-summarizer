package com.braymond.summarizer.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GitHubUser(
        @JsonProperty("login") String login,
        @JsonProperty("id") Long id,
        @JsonProperty("node_id") String nodeId,
        @JsonProperty("avatar_url") String avatarUrl,
        @JsonProperty("gravatar_id") String gravatarId,
        @JsonProperty("url") String url,
        @JsonProperty("html_url") String htmlUrl,
        @JsonProperty("followers_url") String followersUrl,
        @JsonProperty("following_url") String followingUrl,
        @JsonProperty("gists_url") String gistsUrl,
        @JsonProperty("starred_url") String starredUrl,
        @JsonProperty("subscriptions_url") String subscriptionsUrl,
        @JsonProperty("organizations_url") String organizationsUrl,
        @JsonProperty("repos_url") String reposUrl,
        @JsonProperty("events_url") String eventsUrl,
        @JsonProperty("received_events_url") String receivedEventsUrl,
        @JsonProperty("type") String type,
        @JsonProperty("user_view_type") String userViewType,
        @JsonProperty("site_admin") Boolean siteAdmin,
        @JsonProperty("name") String name,
        @JsonProperty("company") String company,
        @JsonProperty("blog") String blog,
        @JsonProperty("location") String location,
        @JsonProperty("email") String email,
        @JsonProperty("hireable") Boolean hireable,
        @JsonProperty("bio") String bio,
        @JsonProperty("twitter_username") String twitterUsername,
        @JsonProperty("public_repos") Integer publicRepos,
        @JsonProperty("public_gists") Integer publicGists,
        @JsonProperty("followers") Integer followers,
        @JsonProperty("following") Integer following,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("updated_at") OffsetDateTime updatedAt) {
}
