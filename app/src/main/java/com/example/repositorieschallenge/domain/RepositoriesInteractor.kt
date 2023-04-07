package com.example.repositorieschallenge.domain

import com.example.repositorieschallenge.data.RepositoriesRepository
import com.example.repositorieschallenge.domain.model.RepositoriesModel

interface RepositoriesInteractor {
    suspend fun getRepositories(): List<RepositoriesModel>
}

class RepositoriesInteractorImpl(
    private val repository: RepositoriesRepository,
    private val mapper: RepositoriesMapper
) : RepositoriesInteractor {
    override suspend fun getRepositories() =
        mapper.toRepositories(repository.getRepositories())
}