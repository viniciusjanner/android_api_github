package com.viniciusjanner.apigithub.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OwnerResponse(
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
) : Serializable
