package com.macrosystems.core.domain

data class RepoEvent(
    val type: String,
    val actorDisplayLogin: String,
    val actorUrl: String
)
