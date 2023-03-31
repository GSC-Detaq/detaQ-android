package com.example.home_presenter.model

import androidx.annotation.StringRes

data class QuickHelp(
    val number: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int
)
