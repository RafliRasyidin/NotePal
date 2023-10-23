package com.rasyidin.notepal.ui.screen.on_boarding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.notepal.data.local.preferences.Preferences
import com.rasyidin.notepal.domain.model.on_boarding.OnBoardingUi
import com.rasyidin.notepal.domain.model.on_boarding.onBoardingUis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val pref: Preferences
) : ViewModel() {

    private var currentPage = mutableStateOf(0)

    var uiState = mutableStateOf(OnBoardingUi())
        private set

    private val _event = Channel<OnBoardingEvent>()
    val event = _event.receiveAsFlow()

    fun setEvent(event: OnBoardingEvent) {
        when (event) {
            OnBoardingEvent.OnNextClick -> {
                currentPage.value += 1
                if (currentPage.value == 3) {
                    viewModelScope.launch {
                        pref.setOnBoardingSession(true)
                        _event.send(OnBoardingEvent.Navigate)
                    }
                    return
                }
                val onBoarding = onBoardingUis[currentPage.value]
                uiState.value = uiState.value.copy(
                    illustration = onBoarding.illustration,
                    headline = onBoarding.headline,
                    textButton = onBoarding.textButton,
                    currentPage = currentPage.value
                )
            }
            OnBoardingEvent.OnPreviousClick -> {
                currentPage.value -= 1
                val onBoarding = onBoardingUis[currentPage.value]
                uiState.value = uiState.value.copy(
                    illustration = onBoarding.illustration,
                    headline = onBoarding.headline,
                    textButton = onBoarding.textButton,
                    currentPage = currentPage.value
                )
            }
            OnBoardingEvent.OnSlideLeft -> {
                currentPage.value += 1
                if (currentPage.value == 3) {
                    viewModelScope.launch {
                        _event.send(OnBoardingEvent.Navigate)
                    }
                    return
                }
                val onBoarding = onBoardingUis[currentPage.value]
                uiState.value = uiState.value.copy(
                    illustration = onBoarding.illustration,
                    headline = onBoarding.headline,
                    textButton = onBoarding.textButton,
                    currentPage = currentPage.value
                )
            }
            OnBoardingEvent.OnSlideRight -> {
                currentPage.value -= 1
                val onBoarding = onBoardingUis[currentPage.value]
                uiState.value = uiState.value.copy(
                    illustration = onBoarding.illustration,
                    headline = onBoarding.headline,
                    textButton = onBoarding.textButton,
                    currentPage = currentPage.value
                )
            }
            else -> Unit
        }
    }
}