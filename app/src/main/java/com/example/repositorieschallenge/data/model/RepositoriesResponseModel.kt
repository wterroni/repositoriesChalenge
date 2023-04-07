package com.example.repositorieschallenge.data.model

data class RepositoriesResponseModel(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)