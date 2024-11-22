package com.macrosystems.list.data

import com.macrosystems.core.domain.RepoData
import com.macrosystems.core.domain.ResponseSource
import com.macrosystems.core.domain.util.DataError
import com.macrosystems.core.data.api.UserGithubAPI
import com.macrosystems.core.data.model.toDomain
import com.macrosystems.core.domain.Result
import com.macrosystems.core.domain.util.responseErrorType
import com.macrosystems.list.domain.UserGithubRepository

class UserGithubRepositoryImpl(
    private val userGithubAPI: UserGithubAPI
): UserGithubRepository {

    override suspend fun fetchRepositories(user: String): Result<List<RepoData>, DataError.Network> {
        val response = userGithubAPI.getUserGithubRepositories(user = user)
        try {
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

}