package ru.coincorn.app.featureIntro.domain.repository

import ru.coincorn.app.featureIntro.data.model.IntroPageModel

interface IntroRepository {

    fun getPages(): List<IntroPageModel>
}