package com.example.repositorieschallenge.component

import androidx.annotation.DrawableRes

data class InfoViewState(
    @DrawableRes val iconRes: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val iconAction: () -> Unit = {}
)