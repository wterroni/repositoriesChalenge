package com.example.repositorieschallenge.common

import com.example.repositorieschallenge.data.RepositoriesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "https://api.github.com/search/"
    fun makeRetrofitService(): RepositoriesService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RepositoriesService::class.java)
    }
}