package com.example.history_presenter.calendar

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.core_ui.ui.theme.Neutral10

enum class CalendarColor {
    CaseX,
    CaseY,
    Selected,
    Today;

    companion object {
        @Composable fun getBackgroundColor(color: CalendarColor): Color {
            return when(color) {
                CaseX -> MaterialTheme.colors.primary
                CaseY ->  MaterialTheme.colors.secondary
                Selected ->  MaterialTheme.colors.secondary
                Today ->  MaterialTheme.colors.primary
            }
        }

        @Composable fun getTextColor(color: CalendarColor): Color {
            return when(color) {
                else -> Neutral10
            }
        }
    }
}