package com.macrosystems.core.data.api

import com.macrosystems.core.data.model.RepoDataModel
import com.macrosystems.core.data.model.RepoEventModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserGithubAPI {

    @GET("users/{user}/repos")
    suspend fun getUserGithubRepositories(
        @Path("user") user: String
    ): Response<List<RepoDataModel>>

    @GET("repos/{user}/{repo}/events")
    suspend fun fetchRepoEvents(
        @Path("user") user: String,
        @Path("repo") repo: String
    ): Response<List<RepoEventModel>>

}