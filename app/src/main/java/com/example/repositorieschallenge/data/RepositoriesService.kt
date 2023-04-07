package com.example.repositorieschallenge.data

import com.example.repositorieschallenge.data.model.RepositoriesResponseModel
import retrofit2.http.GET

interface RepositoriesService {
    @GET("repositories?q=language:kotlin&sort=stars&page=1")
    suspend fun getRepositories(
    ): RepositoriesResponseModel
}