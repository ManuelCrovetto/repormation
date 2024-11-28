package com.macrosystems.core.data.networking

import com.macrosystems.core.data.api.UserGithubAPI
import com.macrosystems.core.data.model.toDomain
import com.macrosystems.core.domain.RemoteGitHubDataSource
import com.macrosystems.core.domain.RepoData
import com.macrosystems.core.domain.RepoEvent
import com.macrosystems.core.domain.Result
import com.macrosystems.core.domain.util.DataError
import com.macrosystems.core.domain.util.responseErrorType

class RemoteGitHubDataSourceImpl(
    private val userGithubAPI: UserGithubAPI
): RemoteGitHubDataSource {

    override suspend fun getUserGithubRepositories(user: String): Result<List<RepoData>, DataError.Network> {
        try {
            val response = userGithubAPI.getUserGithubRepositories(user = user)
            if (response.isSuccessful) {
                response.body()?.let { repoList ->
                    return Result.Success(repoList.map { it.toDomain() })
                } ?: run {
                    return Result.Error(DataError.Network.SERVER_ERROR)
                }
            } else {
                // The response status could be checked and mapped to a proper error.
                return Result.Error(responseErrorType(response.code()))
            }
        } catch (e: Exception) {
            // We can dig into the exception in order to fine grain the error handling.
            e.printStackTrace()
            return Result.Error(DataError.Network.NO_INTERNET)
        }
    }

    override suspend fun fetchLatestEvent(
        user: String,
        repoName: String
    ): Result<List<RepoEvent>, DataError.Network> {
        try {
            val response = userGithubAPI.fetchRepoEvents(
                user = user,
                repo = repoName
            )
            if (response.isSuccessful) {
                response.body()?.let { events ->
                    return Result.Success(events.map { it.toDomain() })
                } ?: run {
                    return Result.Error(DataError.Network.SERVER_ERROR)
                }
            } else {
                return Result.Error(responseErrorType(response.code()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(DataError.Network.NO_INTERNET)
        }
    }
}