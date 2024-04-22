package ru.coincorn.app.core.notification

import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import ru.coincorn.app.core.error.ErrorHandler
import ru.coincorn.app.core.error.UnhandledPushException
import javax.inject.Inject

class FirebaseMessagingHandler @Inject constructor(
    private val notificationHelper: NotificationHelper,
    private val pushEventBus: PushEventBus,
) {

    fun handle(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title ?: "CoinCorn"
        val body = remoteMessage.notification?.body
        val data = remoteMessage.data
        when {
            data.isNotEmpty() -> handlePush(title, body, data)
            body != null -> notificationHelper.showCommonNotification(body, data)
            else -> logUnhandledPush("${remoteMessage.notification} ${remoteMessage.data}")
        }
    }

    private fun handlePush(
        title: String?,
        content: String?,
        data: Map<String, String>,
    ) {
        val loginCode = data[KEY_LOGIN_CODE]
        val payloadLink = data[KEY_LINK]
        when {
            loginCode != null -> {
                pushEventBus.emitEvent(PushEvent.AuthLoginCode(loginCode))
                notificationHelper.showCommonNotification(
                    content ?: "",
                    data
                )
            }
            content != null || payloadLink != null -> {
                notificationHelper.showCommonNotification(
                    content ?: "",
                    data
                )
            }

            else -> logUnhandledPush("Firebase push title: $title body: $content data: $data")
        }
    }

    private fun logUnhandledPush(message: String) {
        val logMessage = "Unhandled push $message"
        Log.e("CoinCorn-Notification", logMessage)
        ErrorHandler.logging(UnhandledPushException(logMessage))
    }

    companion object {
        private const val KEY_LOGIN_CODE = "login-code"
        private const val KEY_LINK = "link"
    }
}