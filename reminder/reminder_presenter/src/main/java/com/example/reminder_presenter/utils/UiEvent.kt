package com.example.reminder_presenter.utils

import com.example.core.utils.UiText

sealed class UiEvent {
    data class Success(val id: String): UiEvent()
    object NavigateUp: UiEvent()
    data class ShowSnackBar(val message: UiText): UiEvent()
}
