package com.macrosystems.detail.data

import com.macrosystems.core.domain.RemoteGitHubDataSource
import com.macrosystems.core.domain.RepoEvent
import com.macrosystems.core.domain.Result
import com.macrosystems.core.domain.util.DataError
import com.macrosystems.detail.domain.EventsRepository

class EventsRepositoryImpl(
    private val remoteGitHubDataSource: RemoteGitHubDataSource
): EventsRepository {

    override suspend fun fetchLatestEvent(
        user: String,
        repoName: String
    ): Result<List<RepoEvent>, DataError.Network> {
        return when(val result = remoteGitHubDataSource.fetchLatestEvent(user, repoName)) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(result.data)
        }
    }
}