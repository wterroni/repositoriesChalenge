package com.example.repositorieschallenge.domain.model

data class RepositoriesModel(
    val repoName: String,
    val starsCount: Int,
    val forkCount: Int,
    val imageUrl: String,
    val authorName: String
)