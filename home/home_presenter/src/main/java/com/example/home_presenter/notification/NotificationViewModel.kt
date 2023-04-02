package com.example.home_presenter.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.utils.Resource
import com.example.home_domain.model.Notification
import com.example.home_domain.use_cases.GetNotifications
import com.example.home_domain.use_cases.UpdateNotificationStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotifications: GetNotifications,
    private val updateNotificationStatus: UpdateNotificationStatus
): ViewModel() {

    val notifications: Flow<PagingData<Notification>> = getNotifications()
        .cachedIn(viewModelScope)

    fun onEvent(event: NotificationEvent) {
        when (event) {
            is NotificationEvent.OnOpenNotification -> {
                viewModelScope.launch {
                    when (
                        val result = updateNotificationStatus(
                            notificationId = event.notificationId
                        )
                    ) {
                        is Resource.Error -> {
                            Timber.d(result.message)
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> Unit
                    }
                }
            }
        }
    }
}