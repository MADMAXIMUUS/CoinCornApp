package ru.coincorn.app.featureIntro.presentation

import ru.coincorn.app.featureIntro.data.model.IntroPageModel

data class IntroScreenState(
    val pages: List<IntroPageModel> = emptyList(),
    val currentPage: Int = 0
)
