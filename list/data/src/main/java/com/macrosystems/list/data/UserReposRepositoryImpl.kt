package com.macrosystems.list.data

import com.macrosystems.core.domain.RemoteGitHubDataSource
import com.macrosystems.core.domain.RepoData
import com.macrosystems.core.domain.Result
import com.macrosystems.core.domain.util.DataError
import com.macrosystems.list.domain.UserReposRepository

class UserReposRepositoryImpl(
    private val remoteGitHubDataSource: RemoteGitHubDataSource
): UserReposRepository {

    override suspend fun fetchRepositories(user: String): Result<List<RepoData>, DataError.Network> {
        return when(val result = remoteGitHubDataSource.getUserGithubRepositories(user)) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(result.data)
        }
    }

}