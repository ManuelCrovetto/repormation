package com.macrosystems.core.data.model

import com.google.gson.annotations.SerializedName

data class OwnerModel(
    val id: Int,
    val login: String,
    @SerializedName("avatar_url") val profilePictureUrl: String,
    val url: String
)
