package com.braymond.summarizer.model;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GitHubRepo(
        @JsonProperty("id") Long id,
        @JsonProperty("node_id") String nodeId,
        @JsonProperty("name") String name,
        @JsonProperty("full_name") String fullName,
        @JsonProperty("private") Boolean isPrivate,
        @JsonProperty("owner") GitHubUser owner,
        @JsonProperty("html_url") String htmlUrl,
        @JsonProperty("description") String description,
        @JsonProperty("fork") Boolean fork,
        @JsonProperty("url") String url,
        @JsonProperty("forks_url") String forksUrl,
        @JsonProperty("keys_url") String keysUrl,
        @JsonProperty("collaborators_url") String collaboratorsUrl,
        @JsonProperty("teams_url") String teamsUrl,
        @JsonProperty("hooks_url") String hooksUrl,
        @JsonProperty("issue_events_url") String issueEventsUrl,
        @JsonProperty("events_url") String eventsUrl,
        @JsonProperty("assignees_url") String assigneesUrl,
        @JsonProperty("branches_url") String branchesUrl,
        @JsonProperty("tags_url") String tagsUrl,
        @JsonProperty("blobs_url") String blobsUrl,
        @JsonProperty("git_tags_url") String gitTagsUrl,
        @JsonProperty("git_refs_url") String gitRefsUrl,
        @JsonProperty("trees_url") String treesUrl,
        @JsonProperty("statuses_url") String statusesUrl,
        @JsonProperty("languages_url") String languagesUrl,
        @JsonProperty("stargazers_url") String stargazersUrl,
        @JsonProperty("contributors_url") String contributorsUrl,
        @JsonProperty("subscribers_url") String subscribersUrl,
        @JsonProperty("subscription_url") String subscriptionUrl,
        @JsonProperty("commits_url") String commitsUrl,
        @JsonProperty("git_commits_url") String gitCommitsUrl,
        @JsonProperty("comments_url") String commentsUrl,
        @JsonProperty("issue_comment_url") String issueCommentUrl,
        @JsonProperty("contents_url") String contentsUrl,
        @JsonProperty("compare_url") String compareUrl,
        @JsonProperty("merges_url") String mergesUrl,
        @JsonProperty("archive_url") String archiveUrl,
        @JsonProperty("downloads_url") String downloadsUrl,
        @JsonProperty("issues_url") String issuesUrl,
        @JsonProperty("pulls_url") String pullsUrl,
        @JsonProperty("milestones_url") String milestonesUrl,
        @JsonProperty("notifications_url") String notificationsUrl,
        @JsonProperty("labels_url") String labelsUrl,
        @JsonProperty("releases_url") String releasesUrl,
        @JsonProperty("deployments_url") String deploymentsUrl,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("updated_at") OffsetDateTime updatedAt,
        @JsonProperty("pushed_at") OffsetDateTime pushedAt,
        @JsonProperty("git_url") String gitUrl,
        @JsonProperty("ssh_url") String sshUrl,
        @JsonProperty("clone_url") String cloneUrl,
        @JsonProperty("svn_url") String svnUrl,
        @JsonProperty("homepage") String homepage,
        @JsonProperty("size") Integer size,
        @JsonProperty("stargazers_count") Integer stargazersCount,
        @JsonProperty("watchers_count") Integer watchersCount,
        @JsonProperty("language") String language,
        @JsonProperty("has_issues") Boolean hasIssues,
        @JsonProperty("has_projects") Boolean hasProjects,
        @JsonProperty("has_downloads") Boolean hasDownloads,
        @JsonProperty("has_wiki") Boolean hasWiki,
        @JsonProperty("has_pages") Boolean hasPages,
        @JsonProperty("has_discussions") Boolean hasDiscussions,
        @JsonProperty("forks_count") Integer forksCount,
        @JsonProperty("mirror_url") String mirrorUrl,
        @JsonProperty("archived") Boolean archived,
        @JsonProperty("disabled") Boolean disabled,
        @JsonProperty("open_issues_count") Integer openIssuesCount,
        @JsonProperty("license") License license,
        @JsonProperty("allow_forking") Boolean allowForking,
        @JsonProperty("is_template") Boolean isTemplate,
        @JsonProperty("web_commit_signoff_required") Boolean webCommitSignoffRequired,
        @JsonProperty("topics") List<String> topics,
        @JsonProperty("visibility") String visibility,
        @JsonProperty("forks") Integer forks,
        @JsonProperty("open_issues") Integer openIssues,
        @JsonProperty("watchers") Integer watchers,
        @JsonProperty("default_branch") String defaultBranch) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record License(
            @JsonProperty("key") String key,
            @JsonProperty("name") String name,
            @JsonProperty("spdx_id") String spdxId,
            @JsonProperty("url") String url,
            @JsonProperty("node_id") String nodeId) {
    }
}
