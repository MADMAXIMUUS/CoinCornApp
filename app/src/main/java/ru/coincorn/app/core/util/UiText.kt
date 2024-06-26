package ru.coincorn.app.core.util

import androidx.annotation.StringRes

sealed class UiText {
    data class PlainText(val value: String) : UiText()
    data class StringResource(@StringRes val id: Int) : UiText()
}
