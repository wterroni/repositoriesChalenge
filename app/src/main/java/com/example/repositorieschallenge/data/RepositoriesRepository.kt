package com.example.repositorieschallenge.data

import com.example.repositorieschallenge.data.model.RepositoriesResponseModel

interface RepositoriesRepository {
    suspend fun getRepositories(page: String): RepositoriesResponseModel
}
class RepositoriesRepositoryImpl(
    private val service: RepositoriesService
): RepositoriesRepository {
    override suspend fun getRepositories(page: String) = service.getRepositories(page)
}