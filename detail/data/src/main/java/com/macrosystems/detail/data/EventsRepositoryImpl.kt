package com.macrosystems.detail.data

import com.macrosystems.core.data.api.UserGithubAPI
import com.macrosystems.core.data.model.toDomain
import com.macrosystems.core.domain.RepoEvent
import com.macrosystems.core.domain.Result
import com.macrosystems.core.domain.util.DataError
import com.macrosystems.core.domain.util.responseErrorType
import com.macrosystems.detail.domain.EventsRepository

class EventsRepositoryImpl(
    private val userGithubAPI: UserGithubAPI
): EventsRepository {

    override suspend fun fetchLatestEvent(
        user: String,
        repoName: String
    ): Result<List<RepoEvent>, DataError.Network> {
        val response = userGithubAPI.fetchRepoEvents(
            user = user,
            repo = repoName
        )
        try {
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