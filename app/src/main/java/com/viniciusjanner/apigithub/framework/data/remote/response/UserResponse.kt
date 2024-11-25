package com.viniciusjanner.apigithub.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponse(
    @SerializedName("login") val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?
) : Serializable
