package com.macrosystems.core.data.model

import com.google.gson.annotations.SerializedName

data class ActorModel(
    @SerializedName("display_login") val displayLogin: String,
    val url: String
)
