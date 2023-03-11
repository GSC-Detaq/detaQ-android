package com.example.detaq.navigation

import com.example.detaq.R

enum class TopLevelDestination(
    val selectedIconRes: Int,
    val unselectedIconRes: Int
) {
    Home(
        selectedIconRes = R.drawable.home_selected,
        unselectedIconRes = R.drawable.home_unselected
    ),
    History(
        selectedIconRes = R.drawable.history_selected,
        unselectedIconRes = R.drawable.history_unselected
    ),
    Reminder(
        selectedIconRes = R.drawable.reminder_selected,
        unselectedIconRes = R.drawable.reminder_unselected
    ),
    Profile(
        selectedIconRes = R.drawable.profile_selected,
        unselectedIconRes = R.drawable.profile_unselected
    );

    companion object{
        fun fromRoute(route: String?): TopLevelDestination? =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                History.name -> History
                Reminder.name -> Reminder
                Profile.name -> Profile
                else -> null
            }
    }
}