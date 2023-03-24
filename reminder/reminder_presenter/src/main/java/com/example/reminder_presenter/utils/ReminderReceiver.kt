package com.example.reminder_presenter.utils

import android.Manifest
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import com.example.reminder_presenter.model.Time
import com.example.core_ui.R as coreR
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ReminderReceiver: BroadcastReceiver() {

    @Inject
    @ApplicationContext lateinit var appContext: Context

    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {
        val title = intent?.getStringExtra(TITLE) ?: "Reminder"
        val description = intent?.getStringExtra(DESCRIPTION) ?: "Click to open and see!"
        val requestCode = intent?.getIntExtra(REQUEST_CODE, 0) ?: 0

        sendNotification(
            context = context ?: appContext,
            title = title,
            message = description,
            requestCode = requestCode
        )
    }

    private fun sendNotification(
        context: Context,
        title: String,
        message:String,
        requestCode: Int
    ){
        val pendingIntent = getPendingIntent(context, requestCode)
        val notificationManager = context.getSystemService<NotificationManager>()

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(coreR.drawable.detaq_logo)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

            builder.setChannelId(CHANNEL_ID)

            notificationManager?.createNotificationChannel(channel)

        }

        val notification = builder.build()

        notificationManager?.notify(requestCode, notification)
    }

    private fun getPendingIntent(
        context: Context,
        requestCode: Int
    ): PendingIntent? {
        val intent = Intent(
            Intent.ACTION_VIEW,
            "detaq://reminder".toUri()
        )

        return TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(requestCode, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    companion object{
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "daily_reminder"
        private const val TITLE = "REMINDER_TITLE"
        private const val DESCRIPTION = "REMINDER_DESCRIPTION"
        private const val REQUEST_CODE = "REMINDER_REQUEST_CODE"

        fun setReminders(
            context: Context,
            dates: List<LocalDate>,
            times: List<Time>,
            title: String,
            description: String,
            id: Int
        ) {
            dates.forEachIndexed { iDate, date ->
                times.forEachIndexed { iTime, time ->
                    val reminderId = (id * 100) + (10 * iDate) + iTime

                    setReminder(
                        context = context,
                        date = date,
                        time = time,
                        id = reminderId,
                        title = title,
                        description = description
                    )
                }
            }
        }

        private fun setReminder(
            context: Context,
            date: LocalDate,
            time: Time,
            id: Int,
            title: String,
            description: String
        ){
            val alarmManager = context.getSystemService<AlarmManager>()

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, date.year)
            calendar.set(Calendar.MONTH, date.month.value - 1)
            calendar.set(Calendar.DAY_OF_MONTH, date.dayOfMonth)
            calendar.set(Calendar.HOUR_OF_DAY, time.hour)
            calendar.set(Calendar.MINUTE, time.minute)
            calendar.set(Calendar.SECOND, 0)

            val calendarInMillis = calendar.timeInMillis

            val intent = Intent(context, ReminderReceiver::class.java)
            intent.putExtra(TITLE, title)
            intent.putExtra(DESCRIPTION, description)
            intent.putExtra(REQUEST_CODE, id)

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                id,
                intent,
                0
            )

            alarmManager?.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendarInMillis,
                pendingIntent
            )

            Timber.d("Alarm Set: $calendarInMillis")
        }

        fun cancelReminder(
            context: Context,
            dateIndex: Int,
            timeIndex: Int,
            id: Int
        ) {
            val alarmManager = context.getSystemService<AlarmManager>()
            val reminderId = (id * 100) + (10 * dateIndex) + timeIndex
            val intent = Intent(context, ReminderReceiver::class.java)
            val pendingIntent =  PendingIntent.getBroadcast(
                context,
                reminderId,
                intent,
                0
            )

            alarmManager?.cancel(pendingIntent)
        }

        fun hasSchedulePermission(context: Context): Boolean {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ContextCompat.checkSelfPermission(context, Manifest.permission.SCHEDULE_EXACT_ALARM) ==
                        PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
        }

        @RequiresApi(Build.VERSION_CODES.S)
        fun requestSchedulePermission(context: Context, activity: Activity) {
            if (!hasSchedulePermission(context)) {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.SCHEDULE_EXACT_ALARM), 1)
            }
        }
    }
}