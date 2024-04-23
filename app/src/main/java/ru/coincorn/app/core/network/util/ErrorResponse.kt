package ru.coincorn.app.core.network.util

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    val title: String? = null,
    val localizedMessage: String? = null,
    val status: Int? = null,
    val code: Int? = null,
    @SerializedName("display_size") val displaySize: String = "full_screen"
)