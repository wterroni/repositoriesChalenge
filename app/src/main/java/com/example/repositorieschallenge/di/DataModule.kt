package com.example.repositorieschallenge.di

import com.example.repositorieschallenge.common.RetrofitFactory
import com.example.repositorieschallenge.data.RepositoriesService
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitFactory.makeRetrofitService() } bind RepositoriesService::class
}