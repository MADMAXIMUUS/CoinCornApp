package ru.coincorn.app.core.error

import androidx.annotation.StringRes

open class MessagedWithResourceException(@StringRes val res: Int) : Exception()

class UnhandledPushException(message: String) : Exception(message)