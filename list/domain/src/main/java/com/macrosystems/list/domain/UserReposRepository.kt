package com.macrosystems.list.domain

import com.macrosystems.core.domain.RepoData
import com.macrosystems.core.domain.Result
import com.macrosystems.core.domain.util.DataError

interface UserReposRepository {

    suspend fun fetchRepositories(user: String): Result<List<RepoData>, DataError.Network>

}