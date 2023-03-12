package com.example.home_presenter.first_aid

import com.example.home_presenter.R
import com.example.home_presenter.model.QuickHelp

data class FirstAidState(
    val consciousFirstAids: List<QuickHelp> = consciousFirstAidsList,
    val unconsciousFirstAids: List<QuickHelp> = unconsciousFirstAidsList
)

private val consciousFirstAidsList = listOf(
    QuickHelp(
        number = 1,
        titleRes = R.string.conscious_title_1,
        descriptionRes = R.string.conscious_description_1
    ),
    QuickHelp(
        number = 2,
        titleRes = R.string.conscious_title_2,
        descriptionRes = R.string.conscious_description_2
    ),
    QuickHelp(
        number = 3,
        titleRes = R.string.conscious_title_3,
        descriptionRes = R.string.conscious_description_3
    ),
    QuickHelp(
        number = 4,
        titleRes = R.string.conscious_title_4,
        descriptionRes = R.string.conscious_description_4
    ),
    QuickHelp(
        number = 5,
        titleRes = R.string.conscious_title_4,
        descriptionRes = R.string.conscious_description_4
    )
)


private val unconsciousFirstAidsList = listOf(
    QuickHelp(
        number = 1,
        titleRes = R.string.unconscious_title_1,
        descriptionRes = R.string.unconscious_description_1
    ),
    QuickHelp(
        number = 2,
        titleRes = R.string.unconscious_title_2,
        descriptionRes = R.string.unconscious_description_2
    ),
    QuickHelp(
        number = 3,
        titleRes = R.string.unconscious_title_3,
        descriptionRes = R.string.unconscious_description_3
    )
)

enum class FirstAidSection {
    Conscious,
    Unconscious
}