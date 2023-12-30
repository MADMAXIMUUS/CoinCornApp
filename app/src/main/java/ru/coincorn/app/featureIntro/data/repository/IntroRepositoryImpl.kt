package ru.coincorn.app.featureIntro.data.repository

import ru.coincorn.app.featureIntro.data.dataSource.IntroDataSource
import ru.coincorn.app.featureIntro.data.model.IntroPageModel
import ru.coincorn.app.featureIntro.domain.repository.IntroRepository
import javax.inject.Inject

class IntroRepositoryImpl @Inject constructor(
    private val dataSource: IntroDataSource
) : IntroRepository {

    override fun getPages(): List<IntroPageModel> = dataSource.getIntroScreens()
}