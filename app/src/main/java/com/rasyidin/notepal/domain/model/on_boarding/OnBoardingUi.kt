package com.rasyidin.notepal.domain.model.on_boarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rasyidin.notepal.R

data class OnBoardingUi(
    @DrawableRes val illustration: Int = R.drawable.illustration_1,
    @StringRes val headline: Int = R.string.on_boarding_1,
    @StringRes val textButton: Int = R.string.next,
    val currentPage: Int = 0
)

val onBoardingUis = listOf(
    OnBoardingUi(
        illustration = R.drawable.illustration_1,
        headline = R.string.on_boarding_1,
        textButton = R.string.next,
    ),
    OnBoardingUi(
        illustration = R.drawable.illustration_2,
        headline = R.string.on_boarding_2,
        textButton = R.string.next
    ),
    OnBoardingUi(
        illustration = R.drawable.illustration_4,
        headline = R.string.on_boarding_3,
        textButton = R.string.lets_get_started
    ),
)
