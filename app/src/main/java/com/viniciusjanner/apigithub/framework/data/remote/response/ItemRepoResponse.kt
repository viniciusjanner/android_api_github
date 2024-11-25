package com.viniciusjanner.apigithub.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import java.io.Serializable

data class ItemRepoResponse(
    val id: Long?,
    val name: String?,
    val owner: OwnerResponse?,
    val description: String?,
    @SerializedName("stargazers_count")
    val stargazersCount: Long?,
    @SerializedName("forks_count")
    val forksCount: Long?,
) : Serializable

fun ItemRepoResponse.toItemRepoModel(): ItemRepoModel {
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
