package com.example.core.domain.preferences

import com.example.core.BuildConfig

interface Preferences {
    fun saveShouldShowOnBoarding(shouldShow: Boolean)
    fun loadShouldShowOnBoarding(): Boolean

    companion object {
        const val SHOULD_SHOW_ON_BOARDING = BuildConfig.SHOW_ON_BOARDING_KEY
    }
}