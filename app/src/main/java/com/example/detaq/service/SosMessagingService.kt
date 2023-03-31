package com.example.detaq.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.getSystemService
import com.example.core.domain.preferences.TokenPreferences
import com.example.core.domain.use_cases.UpdateFcmToken
import com.example.core_ui.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SosMessagingService: FirebaseMessagingService() {

    @Inject
    @ApplicationContext
    lateinit var appContext: Context

    @Inject
    lateinit var updateFcmToken: UpdateFcmToken

    @Inject
    lateinit var tokenPreferences: TokenPreferences

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        if (tokenPreferences.getToken().isNotEmpty()) {
            scope.launch {
                updateFcmToken(token)
            }
        }

        tokenPreferences.setFcmToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        sendNotification(
            title = message.notification?.title ?: "Sos!",
            message = message.notification?.body ?: "Sos notification!",
            link = message.notification?.link
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun sendNotification(
        title: String,
        message:String,
        link: Uri?
    ){
        val pendingIntent = getPendingIntent(
            context = appContext,
            uri = link
        )
        val notificationManager = appContext.getSystemService<NotificationManager>()

        val builder = NotificationCompat.Builder(appContext, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.detaq_logo)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

            builder.setChannelId(CHANNEL_ID)

            notificationManager?.createNotificationChannel(channel)

        }

        val notification = builder.build()
        val notificationId = ((Date().time / 1000L) % Integer.MAX_VALUE).toInt()

        notificationManager?.notify(notificationId, notification)
    }

    private fun getPendingIntent(
        context: Context,
        uri: Uri?
    ): PendingIntent? {
        Timber.d(uri.toString())

        val intent = Intent(
            Intent.ACTION_VIEW,
            uri
        )

        return TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    companion object {
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "daily_reminder"
    }
}