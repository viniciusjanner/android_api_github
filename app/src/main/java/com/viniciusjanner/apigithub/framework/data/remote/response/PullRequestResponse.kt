package com.viniciusjanner.apigithub.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import com.viniciusjanner.domain.model.PullRequestModel
import java.io.Serializable

data class PullRequestResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("created_at") val dateCreated: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("body") val body: String?,
    @SerializedName("user") val user: UserResponse?,
    @SerializedName("state") val state: String?
) : Serializable

fun PullRequestResponse.toModel(): PullRequestModel {
    return PullRequestModel(
        id = this.id,
        dateCreated = this.dateCreated,
        title = this.title,
        body = this.body,
        userName = this.user?.login,
        userAvatarUrl = this.user?.avatarUrl,
        state = this.state,
    )
}
