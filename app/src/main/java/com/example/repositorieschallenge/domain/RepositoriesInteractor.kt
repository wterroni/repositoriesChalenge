package com.example.repositorieschallenge.domain

import com.example.repositorieschallenge.data.RepositoriesRepository
import com.example.repositorieschallenge.domain.model.RepositoriesModel

interface RepositoriesInteractor {
    suspend fun getRepositories(page: String): List<RepositoriesModel>
}

class RepositoriesInteractorImpl(
    private val repository: RepositoriesRepository,
    private val mapper: RepositoriesMapper
) : RepositoriesInteractor {
    override suspend fun getRepositories(page: String) =
        mapper.toRepositories(repository.getRepositories(page))
}