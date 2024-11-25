package com.viniciusjanner.apigithub.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepoListResponse(
    @SerializedName("total_count") val totalCount: Long?,
    @SerializedName("incomplete_results") val isIncompleteResults: Boolean?,
    @SerializedName("items") val items: List<ItemRepoResponse>?
) : Serializable
