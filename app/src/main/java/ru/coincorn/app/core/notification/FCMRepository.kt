package ru.coincorn.app.core.notification

import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FcmRepository @Inject constructor() {
    fun getDeviceToken(): Flow<String> = flow {
        val a = FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                //TODO
            }
        }.await()
        emit(a)
    }
}