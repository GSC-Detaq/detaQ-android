package com.example.reminder_presenter.utils

import android.Manifest
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import com.example.core.domain.dispatchers.DispatchersProvider
import com.example.core.domain.model.Time
import com.example.reminder_domain.use_cases.AddMedicineReminderNotification
import com.example.reminder_domain.use_cases.EndDoctorReminder
import com.example.reminder_domain.use_cases.EndMedicineReminder
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import java.util.*
import javax.inject.Inject
import com.example.core_ui.R as coreR

@AndroidEntryPoint
class ReminderReceiver: BroadcastReceiver() {

    @Inject
    @ApplicationContext lateinit var appContext: Context

    @Inject
    lateinit var dispatchers: DispatchersProvider

    @Inject
    lateinit var addMedicineReminderNotification: AddMedicineReminderNotification

    @Inject
    lateinit var addDoctorReminderNotification: AddMedicineReminderNotification

    @Inject
    lateinit var endMedicineReminder: EndMedicineReminder

    @Inject
    lateinit var endDoctorReminder: EndDoctorReminder

    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {
        val title = intent?.getStringExtra(TITLE) ?: "Reminder"
        val description = intent?.getStringExtra(DESCRIPTION) ?: "Click to open and see!"
        val type = intent?.getStringExtra(TYPE) ?: "1"
        val endTime = intent?.getBooleanExtra(END_TIME, false) ?: false
        val reminderId = intent?.getStringExtra(REMINDER_ID) ?: ""

        sendNotification(
            context = context ?: appContext,
            title = title,
            message = description
        )

        CoroutineScope(dispatchers.io).launch {
            try {
                if (type == "1") {
                    addMedicineReminderNotification(
                        title = title,
                        body = description
                    )
                } else {
                    addDoctorReminderNotification(
                        title = title,
                        body = description
                    )
                }

                if (endTime) {
                    if (type == "1") {
                        endMedicineReminder(
                            reminderId = reminderId
                        )
                    } else {
                        endDoctorReminder(
                            reminderId = reminderId
                        )
                    }
                }
            } finally {
                cancel()
            }
        }
    }

    private fun sendNotification(
        context: Context,
        title: String,
        message:String
    ){
        val pendingIntent = getPendingIntent(context)
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
        val notificationId = ((Date().time / 1000L) % Integer.MAX_VALUE).toInt()

        notificationManager?.notify(notificationId, notification)
    }

    private fun getPendingIntent(
        context: Context
    ): PendingIntent? {
        val intent = Intent(
            Intent.ACTION_VIEW,
            "detaq://reminder".toUri()
        )

        return TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    companion object{
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "daily_reminder"
        private const val TITLE = "REMINDER_TITLE"
        private const val DESCRIPTION = "REMINDER_DESCRIPTION"
        private const val TYPE = "REMINDER_TYPE"
        private const val END_TIME = "END_TIME"
        private const val REMINDER_ID = "REMINDER_ID"

        // type
        // 1 -> medicine, 2 -> doctor

        fun setReminders(
            context: Context,
            dates: List<LocalDate>,
            times: List<Time>,
            title: String,
            description: String,
            type: String,
            id: String
        ) {
            dates.forEachIndexed { iDate, date ->
                times
                    .sortedWith(
                        compareBy(
                            { it.hour },
                            { it.minute }
                        )
                    )
                    .forEachIndexed { iTime, time ->
                        val reminderId = "$id-$iDate-$iTime"

                        setReminder(
                            context = context,
                            date = date,
                            time = time,
                            id = reminderId,
                            title = title,
                            type = type,
                            description = description,
                            isLast = iDate == dates.size-1 && iTime == times.size-1,
                            reminderId = id
                        )
                    }
            }
        }

        private fun setReminder(
            context: Context,
            date: LocalDate,
            time: Time,
            id: String,
            title: String,
            description: String,
            type: String,
            isLast: Boolean,
            reminderId: String
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
            intent.putExtra(TYPE, type)
            intent.putExtra(END_TIME, isLast)
            intent.putExtra(REMINDER_ID, reminderId)

            intent.data = Uri.parse("scheme://$id")

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            alarmManager?.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendarInMillis,
                pendingIntent
            )

            Timber.d("Alarm Set: $calendarInMillis id: $id")
        }

        fun cancelReminder(
            context: Context,
            dateIndex: Int,
            timeIndex: Int,
            id: String
        ) {
            val alarmManager = context.getSystemService<AlarmManager>()
            val reminderId = "$id-$dateIndex-$timeIndex"

            val intent = Intent(context, ReminderReceiver::class.java)
            intent.data = Uri.parse("scheme://$reminderId")

            val pendingIntent =  PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            alarmManager?.cancel(pendingIntent)

            Timber.d("Alarm Canceled id: $id-$dateIndex-$timeIndex")
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