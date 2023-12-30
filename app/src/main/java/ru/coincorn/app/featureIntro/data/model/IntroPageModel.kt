package ru.coincorn.app.featureIntro.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class IntroPageModel(
    val position: Int,
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val text: Int,
)