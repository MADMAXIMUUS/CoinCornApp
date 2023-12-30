package ru.coincorn.app.featureIntro.data.dataSource

import ru.coincorn.app.R
import ru.coincorn.app.featureIntro.data.model.IntroPageModel
import javax.inject.Inject

class IntroDataSource @Inject constructor() {

    fun getIntroScreens(): List<IntroPageModel> = listOf(
        IntroPageModel(
            position = 0,
            image = R.drawable.img_intro_1,
            title = R.string.intro_page_title_1,
            text = R.string.intro_page_text_1
        ),
        IntroPageModel(
            position = 1,
            image = R.drawable.img_intro_2,
            title = R.string.intro_page_title_2,
            text = R.string.intro_page_text_2
        ),
        IntroPageModel(
            position = 2,
            image = R.drawable.img_intro_3,
            title = R.string.intro_page_title_3,
            text = R.string.intro_page_text_3
        ),
    )
}