package com.example.home_presenter.independent_handling

import com.example.home_presenter.R
import com.example.home_presenter.model.QuickHelp

data class IndependentHandlingState(
    val helps: List<QuickHelp> = independentHandlingHelps
)

private val independentHandlingHelps = listOf(
    QuickHelp(
        number = 1,
        titleRes = R.string.independent_handling_title_1,
        descriptionRes = R.string.independent_handling_description_1
    ),
    QuickHelp(
        number = 2,
        titleRes = R.string.independent_handling_title_2,
        descriptionRes = R.string.independent_handling_description_2
    ),
    QuickHelp(
        number = 3,
        titleRes = R.string.independent_handling_title_3,
        descriptionRes = R.string.independent_handling_description_3
    ),
    QuickHelp(
        number = 4,
        titleRes = R.string.independent_handling_title_4,
        descriptionRes = R.string.independent_handling_description_4
    )
)