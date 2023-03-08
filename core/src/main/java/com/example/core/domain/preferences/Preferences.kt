package com.example.core.domain.preferences

interface Preferences {
    fun saveShouldShowOnBoarding(shouldShow: Boolean)
    fun loadShouldShowOnBoarding(): Boolean

    companion object {
        const val SHOULD_SHOW_ON_BOARDING = "SHOW_ON_BOARDING"
    }
}