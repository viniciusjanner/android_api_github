package com.viniciusjanner.apigithub.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepoListResponse(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("incomplete_results") val isIncompleteResults: Boolean?,
    @SerializedName("items") val items: List<RepoItemResponse>?
) : Serializable
