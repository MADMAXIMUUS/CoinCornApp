package ru.coincorn.app.core.error.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HttpErrorModel(
    val title: String? = null,
    val message: String? = null,
    val errorCode: Int? = null,
    val statusCode: Int? = null,
) : Parcelable