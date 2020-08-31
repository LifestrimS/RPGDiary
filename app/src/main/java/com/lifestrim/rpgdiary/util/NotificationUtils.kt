package com.lifestrim.rpgdiary.util

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.view.View
import com.lifestrim.rpgdiary.notification.AlarmReceiver
import java.util.*

class NotificationUtils {

    fun setNotification(view: View, timeInMilliSeconds: Long, taskTitle: String) {

        if (timeInMilliSeconds > 0) {

            val alarmManager = view.context.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(view.context.applicationContext, AlarmReceiver::class.java)

            alarmIntent.putExtra("timestamp", timeInMilliSeconds)
            alarmIntent.putExtra("taskTitle", taskTitle)

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeInMilliSeconds

            val pendingIntent = PendingIntent.getBroadcast(
                view.context,
                0,
                alarmIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        }

    }

}