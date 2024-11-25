package com.viniciusjanner.apigithub.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepoPullRequestResponse(
    @SerializedName("items") val items: List<PullRequestResponse>?
) : Serializable
