package com.example.repositorieschallenge.presentation

sealed class LoadState {
    object Loading: LoadState()
    object Done: LoadState()
}