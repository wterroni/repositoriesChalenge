package com.example.repositorieschallenge.domain

import com.example.repositorieschallenge.data.model.RepositoriesResponseModel
import com.example.repositorieschallenge.domain.model.RepositoriesModel

interface RepositoriesMapper {
    fun toRepositories(repositoriesResponseModel: RepositoriesResponseModel): List<RepositoriesModel>
}

class RepositoriesMapperImpl(
): RepositoriesMapper {
    override fun toRepositories(repositoriesResponseModel: RepositoriesResponseModel): List<RepositoriesModel> {
        return repositoriesResponseModel.items.map {
            RepositoriesModel(
                repoName = it.full_name,
                starsCount = it.stargazers_count,
                forkCount = it.forks_count,
                imageUrl = it.owner.avatar_url,
                authorName = it.name
            )
        }
    }
}