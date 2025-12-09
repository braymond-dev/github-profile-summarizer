package com.braymond.summarizer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubSummary {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("display_name")
    private String displayName;

    private String avatar;

    @JsonProperty("geo_location")
    private String geoLocation;

    private String email;

    private String url;

    @JsonProperty("created_at")
    private String createdAt;

    private List<Repo> repos;

    public GitHubProfile() {
    }

    public GitHubProfile(String userName,
                         String displayName,
                         String avatar,
                         String geoLocation,
                         String email,
                         String url,
                         String createdAt,
                         List<Repo> repos) {
        this.userName = userName;
        this.displayName = displayName;
        this.avatar = avatar;
        this.geoLocation = geoLocation;
        this.email = email;
        this.url = url;
        this.createdAt = createdAt;
        this.repos = repos;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }

    public static class Repo {

        private String name;
        private String url;

        public Repo() {
        }

        public Repo(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
