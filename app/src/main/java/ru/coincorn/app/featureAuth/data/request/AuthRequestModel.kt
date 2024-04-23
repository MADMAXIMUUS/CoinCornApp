package ru.coincorn.app.featureAuth.data.request

import com.google.gson.annotations.SerializedName

data class AuthRequestModel(
    val os: String = "android",
    val email: String = "",
    @SerializedName("fcm_push_token") val fcmPushToken: String = "",
)
