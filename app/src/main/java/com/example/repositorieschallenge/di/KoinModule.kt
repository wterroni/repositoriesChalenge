package com.example.repositorieschallenge.di

import android.content.Context
import org.koin.android.ext.koin.androidContext

fun startKoin(context: Context) {
    org.koin.core.context.startKoin {
        androidContext(context)
        modules(appModules + featureModules)
    }
}

val appModules = listOf(
    dataModule
)

val featureModules = listOf(viewModelsModule)