package com.example.repositorieschallenge.di

import com.example.repositorieschallenge.data.RepositoriesRepository
import com.example.repositorieschallenge.data.RepositoriesRepositoryImpl
import com.example.repositorieschallenge.domain.RepositoriesInteractor
import com.example.repositorieschallenge.domain.RepositoriesInteractorImpl
import com.example.repositorieschallenge.domain.RepositoriesMapper
import com.example.repositorieschallenge.domain.RepositoriesMapperImpl
import com.example.repositorieschallenge.presentation.RepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { RepositoriesViewModel(get()) }

    //factory { LineAdapter() }
    single { RepositoriesInteractorImpl(get(), get()) } bind RepositoriesInteractor::class
    single { RepositoriesRepositoryImpl(get()) } bind RepositoriesRepository::class
    single { RepositoriesMapperImpl() } bind RepositoriesMapper::class
}