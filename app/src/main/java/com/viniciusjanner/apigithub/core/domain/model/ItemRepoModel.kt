package com.viniciusjanner.apigithub.core.domain.model

import java.io.Serializable

data class ItemRepoModel(
    val id: Long?,
    val name: String?,
    val description: String?,
    val forksCount: Long?,
    val stargazersCount: Long?,
    val avatarUrl: String?,
    val userName: String?
) : Serializable
