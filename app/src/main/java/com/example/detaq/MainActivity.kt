package com.example.detaq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.core.domain.preferences.Preferences
import com.example.core_ui.ui.theme.DetaQTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetaQTheme {
                DetaQ(
                    shouldShowOnBoarding = preferences.loadShouldShowOnBoarding()
                )
            }
        }
    }
}