package com.lifestrim.rpgdiary.notification

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import com.lifestrim.rpgdiary.R
import java.util.*

class NotificationService : IntentService("NotificationService") {
    private lateinit var mNotification: Notification
    private val mNotificationId: Int = 1000

    companion object {
        const val CHANNEL_ID = "com.lifestrim.rpgdiary.notification.CHANNEL_ID"
        const val CHANNEL_NAME = "Task reminder"
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {

        val context = this.applicationContext
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        notificationChannel.enableVibration(true)
        notificationChannel.setShowBadge(true)
        notificationChannel.enableLights(true)
        notificationChannel.description = getString(R.string.notification_channel_description)
        notificationManager.createNotificationChannel(notificationChannel)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onHandleIntent(intent: Intent?) {

        createChannel()
        var timestamp: Long = 0
        var message = ""
        if (intent != null && intent.extras != null) {
            timestamp = intent.extras!!.getLong("timestamp")
            message = intent.extras!!.getString("taskTitle").toString()
        }

        if (timestamp > 0) {

            val title = "It's time for your task!?"

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp

            val res = this.resources

            mNotification = Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_image_for_imagebutton)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setContentTitle(title)
                .setStyle(
                    Notification.BigTextStyle()
                        .bigText(message)
                )
                .setContentText(message).build()

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(mNotificationId, mNotification)
        }


    }
}