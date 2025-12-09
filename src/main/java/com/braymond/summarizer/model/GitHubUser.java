package com.braymond.summarizer.model;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubUser(
        @JsonProperty("login") String login,
        @JsonProperty("avatar_url") String avatarUrl,
        @JsonProperty("html_url") String htmlUrl,
        @JsonProperty("name") String name,
        @JsonProperty("location") String location,
        @JsonProperty("email") String email,
        @JsonProperty("created_at") OffsetDateTime createdAt) {
}
