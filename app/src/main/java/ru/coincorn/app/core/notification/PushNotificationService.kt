package ru.coincorn.app.core.notification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors.fromApplication
import dagger.hilt.components.SingletonComponent
import ru.coincorn.app.featureProfile.domain.repository.UserRepository

@AndroidEntryPoint
class PushNotificationService : FirebaseMessagingService() {

    private lateinit var firebaseMessagingHandler: FirebaseMessagingHandler
    private lateinit var userRepository: UserRepository

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    internal interface FirebaseMessagingHandlerEntryPoint {
        fun getFirebaseMessagingHandler(): FirebaseMessagingHandler
        fun getUserRepository(): UserRepository
    }


    override fun onCreate() {
        val provider = fromApplication(
            applicationContext,
            FirebaseMessagingHandlerEntryPoint::class.java
        )
        firebaseMessagingHandler = provider.getFirebaseMessagingHandler()
        userRepository = provider.getUserRepository()
        super.onCreate()
    }

    override fun onNewToken(token: String) {
        userRepository.updateToken(token)
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.e("CoinCorn-Notification", "From: ${message.data}")
        firebaseMessagingHandler.handle(message)
    }
}