package com.rasyidin.notepal.ui.screen.on_boarding

sealed interface OnBoardingEvent {
    data object Idle : OnBoardingEvent
    data object OnNextClick : OnBoardingEvent
    data object OnSlideRight : OnBoardingEvent
    data object OnSlideLeft : OnBoardingEvent
    data object OnPreviousClick : OnBoardingEvent
    data object Navigate : OnBoardingEvent
}