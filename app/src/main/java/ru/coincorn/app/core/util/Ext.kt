package ru.coincorn.app.core.util

import android.content.Context

fun UiText.asString(context: Context): String {
    return when (this) {
        is UiText.PlainText -> this.value
        is UiText.StringResource -> context.getString(this.id)
    }
}