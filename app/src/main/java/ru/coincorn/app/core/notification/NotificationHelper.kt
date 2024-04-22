package ru.coincorn.app.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import ru.coincorn.app.R
import ru.coincorn.app.core.notification.NotificationConst.COMMON_CHANNEL_DESCRIPTION
import ru.coincorn.app.core.notification.NotificationConst.COMMON_CHANNEL_NAME
import ru.coincorn.app.core.notification.NotificationConst.COMMON_NOTIFICATION_ID
import ru.coincorn.app.core.notification.NotificationConst.NOTIFICATION_CHANNEL_ID
import ru.coincorn.app.main.MainActivity
import javax.inject.Inject

class NotificationHelper @Inject constructor(
    private val context: Context,
) {
    private val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showCommonNotification(messageBody: String, data: Map<String, String>) {
        val notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notificaiton)
            .setColor(ContextCompat.getColor(context, R.color.primary))
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(messageBody)
            .setStyle(NotificationCompat.BigTextStyle().bigText(messageBody))
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(getPendingIntentActionOnNotificationTap(data))

        installNotificationChannel()

        notificationManager.notify(COMMON_NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun getPendingIntentActionOnNotificationTap(data: Map<String, String>): PendingIntent {
        val intent = if (data["link"] != null) {
            Intent().apply {
                if (data["link"] != null) {
                    action = Intent.ACTION_VIEW
                    setData(Uri.parse(data["link"]))
                    putExtra("isFromNotification", true)
                }
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        } else {
            Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun installNotificationChannel() {
        createNotificationChannelIfNeed(
            notificationManager = notificationManager,
            channelId = NOTIFICATION_CHANNEL_ID,
            name = COMMON_CHANNEL_NAME,
            importance = NotificationManager.IMPORTANCE_DEFAULT,
            descriptionString = COMMON_CHANNEL_DESCRIPTION
        )
    }

    companion object {
        fun createNotificationChannelIfNeed(
            notificationManager: NotificationManager,
            channelId: String,
            name: String,
            importance: Int,
            descriptionString: String,
        ) {
            val channel = NotificationChannel(
                channelId,
                name,
                importance
            ).apply {
                description = descriptionString
            }
            notificationManager.createNotificationChannel(channel)
        }
    }
}