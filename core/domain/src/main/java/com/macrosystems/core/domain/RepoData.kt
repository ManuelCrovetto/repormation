package com.macrosystems.core.domain

data class RepoData(
    val id: Int,
    val name: String,
    val repoUrl: String,
    val repoDescription: String,
    val owner: Owner,
    val stars: Int,
    val language: String
)
