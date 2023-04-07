package com.example.repositorieschallenge.data

import com.example.repositorieschallenge.data.model.RepositoriesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesService {
    @GET("repositories?q=language:kotlin&sort=stars")
    suspend fun getRepositories(
        @Query("page") page: String
    ): RepositoriesResponseModel
}