package ru.coincorn.app.featureAuth.data.request

import com.google.gson.annotations.SerializedName

data class CodeRequestModel(
    val code: String,
    val os: String = "android",
    val email: String = "",
    @SerializedName("vendor_id") val vendorId: String = ""
)