package com.viniciusjanner.apigithub.core.domain.model

data class PullRequestModel(
    val id: Long?,
    val dateCreated: String?,
    val title: String?,
    val body: String?,
    val userName: String?,
    val userAvatarUrl: String?,
    val state: String?,
)
