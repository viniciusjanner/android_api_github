package com.viniciusjanner.apigithub.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepoLicenseResponse(
    val key: String?,
    val name: String?,
    @SerializedName("spdx_id")
    val spdxId: String?,
    val url: String?,
    @SerializedName("node_id")
    val nodeId: String?
) : Serializable
