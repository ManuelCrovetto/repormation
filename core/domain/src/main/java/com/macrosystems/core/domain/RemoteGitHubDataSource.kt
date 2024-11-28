package com.macrosystems.core.domain

import com.macrosystems.core.domain.util.DataError

interface RemoteGitHubDataSource {

    suspend fun getUserGithubRepositories(
        user: String
    ): Result<List<RepoData>, DataError.Network>

    suspend fun fetchLatestEvent(
        user: String,
        repoName: String
    ): Result<List<RepoEvent>, DataError.Network>

}