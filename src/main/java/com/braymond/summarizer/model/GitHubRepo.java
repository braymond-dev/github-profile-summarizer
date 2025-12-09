package com.braymond.summarizer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubRepo(
        @JsonProperty("name") String name,
        @JsonProperty("html_url") String htmlUrl) {
}
