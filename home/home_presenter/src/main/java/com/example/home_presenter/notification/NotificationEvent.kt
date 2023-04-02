package com.example.home_presenter.notification

sealed class NotificationEvent {
    data class OnOpenNotification(
        val notificationId: String
    ): NotificationEvent()
}