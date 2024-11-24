package com.viniciusjanner.apigithub.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import java.io.Serializable

data class RepoItemResponse(
    val id: Long?,
    @SerializedName("node_id")
    val nodeId: String?,
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    val isPrivate: Boolean?,
    val owner: RepoOwnerResponse?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    val description: String?,
    val isFork: Boolean?,
    val url: String?,
    @SerializedName("forks_url")
    val forksUrl: String?,
    @SerializedName("keys_url")
    val keysUrl: String?,
    @SerializedName("collaborators_url")
    val collaboratorsUrl: String?,
    @SerializedName("teams_url")
    val teamsUrl: String?,
    @SerializedName("hooks_url")
    val hooksUrl: String?,
    @SerializedName("issue_events_url")
    val issueEventsUrl: String?,
    @SerializedName("events_url")
    val eventsUrl: String?,
    @SerializedName("assignees_url")
    val assigneesUrl: String?,
    @SerializedName("branches_url")
    val branchesUrl: String?,
    @SerializedName("tags_url")
    val tagsUrl: String?,
    @SerializedName("blobs_url")
    val blobsUrl: String?,
    @SerializedName("git_tags_url")
    val gitTagsUrl: String?,
    @SerializedName("git_refs_url")
    val gitRefsUrl: String?,
    @SerializedName("trees_url")
    val treesUrl: String?,
    @SerializedName("statuses_url")
    val statusesUrl: String?,
    @SerializedName("languages_url")
    val languagesUrl: String?,
    @SerializedName("stargazers_url")
    val stargazersUrl: String?,
    @SerializedName("contributors_url")
    val contributorsUrl: String?,
    @SerializedName("subscribers_url")
    val subscribersUrl: String?,
    @SerializedName("subscription_url")
    val subscriptionUrl: String?,
    @SerializedName("commits_url")
    val commitsUrl: String?,
    @SerializedName("git_commits_url")
    val gitCommitsUrl: String?,
    @SerializedName("comments_url")
    val commentsUrl: String?,
    @SerializedName("issue_comment_url")
    val issueCommentUrl: String?,
    @SerializedName("contents_url")
    val contentsUrl: String?,
    @SerializedName("compare_url")
    val compareUrl: String?,
    @SerializedName("merges_url")
    val mergesUrl: String?,
    @SerializedName("archive_url")
    val archiveUrl: String?,
    @SerializedName("downloads_url")
    val downloadsUrl: String?,
    @SerializedName("issues_url")
    val issuesUrl: String?,
    @SerializedName("pulls_url")
    val pullsUrl: String?,
    @SerializedName("milestones_url")
    val milestonesUrl: String?,
    @SerializedName("notifications_url")
    val notificationsUrl: String?,
    @SerializedName("labels_url")
    val labelsUrl: String?,
    @SerializedName("releases_url")
    val releasesUrl: String?,
    @SerializedName("deployments_url")
    val deploymentsUrl: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("pushed_at")
    val pushedAt: String?,
    @SerializedName("git_url")
    val gitUrl: String?,
    @SerializedName("ssh_url")
    val sshUrl: String?,
    @SerializedName("clone_url")
    val cloneUrl: String?,
    @SerializedName("svn_url")
    val svnUrl: String?,
    val homepage: String?,
    val size: Long?,
    @SerializedName("stargazers_count")
    val stargazersCount: Long?,
    @SerializedName("watchers_count")
    val watchersCount: Long?,
    val language: String?,
    @SerializedName("has_issues")
    val isHasIssues: Boolean?,
    @SerializedName("has_projects")
    val isHasProjects: Boolean?,
    @SerializedName("has_downloads")
    val isHasDownloads: Boolean?,
    @SerializedName("has_wiki")
    val isHasWiki: Boolean?,
    @SerializedName("has_pages")
    val isHasPages: Boolean?,
    @SerializedName("has_discussions")
    val isHasDiscussions: Boolean?,
    @SerializedName("forks_count")
    val forksCount: Long?,
    @SerializedName("mirror_url")
    val mirrorUrl: Any?,
    val isArchived: Boolean?,
    val isDisabled: Boolean?,
    @SerializedName("open_issues_count")
    val openIssuesCount: Long?,
    val license: RepoLicenseResponse?,
    @SerializedName("allow_forking")
    val isAllowForking: Boolean?,
    @SerializedName("is_template")
    val isTemplate: Boolean?,
    @SerializedName("web_commit_signoff_required")
    val isWebCommitSignoffRequired: Boolean?,
    val topics: List<String>?,
    val visibility: String?,
    val forks: Long?,
    @SerializedName("open_issues")
    val openIssues: Long?,
    val watchers: Long?,
    @SerializedName("default_branch")
    val defaultBranch: String?,
    val score: Double?
) : Serializable

fun RepoItemResponse.toItemRepoModel(): ItemRepoModel {
    return ItemRepoModel(
        id = this.id,
        name = this.name,
        description = this.description,
        forksCount = this.forksCount,
        stargazersCount = this.stargazersCount,
        avatarUrl = this.owner?.avatarUrl,
        userName = this.owner?.login
    )
}
