package com.macrosystems.detail.domain

import com.macrosystems.core.domain.RepoEvent
import com.macrosystems.core.domain.Result
import com.macrosystems.core.domain.util.DataError

interface EventsRepository {
    suspend fun fetchLatestEvent(user: String, repoName: String): Result<List<RepoEvent>, DataError.Network>
}