package com.example.home_domain.use_cases

data class HomeUseCases(
    val getNotifications: GetNotifications,
    val getNotificationCount: GetNotificationCount,
    val updateNotificationStatus: UpdateNotificationStatus
)
