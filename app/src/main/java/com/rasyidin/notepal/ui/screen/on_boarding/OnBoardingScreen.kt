package com.rasyidin.notepal.ui.screen.on_boarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.R
import com.rasyidin.notepal.domain.model.component.ButtonStyles
import com.rasyidin.notepal.domain.model.on_boarding.OnBoardingUi
import com.rasyidin.notepal.domain.model.on_boarding.onBoardingUis
import com.rasyidin.notepal.ui.component.PalButton
import com.rasyidin.notepal.ui.theme.NotePalTheme

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnBoardingViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onNavigate: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        LaunchedEffect(key1 = true) {
            viewModel.event.collect { event ->
                if (event is OnBoardingEvent.Navigate) {
                    onNavigate()
                }
            }
        }
        OnBoardingContent(
            ui = viewModel.uiState.value,
            onNextClick = {
                viewModel.setEvent(OnBoardingEvent.OnNextClick)
            },
            onPreviousClick = {
                viewModel.setEvent(OnBoardingEvent.OnPreviousClick)
            },
            onSlideLeft = {
                viewModel.setEvent(OnBoardingEvent.OnSlideLeft)
            },
            onSlideRight = {
                viewModel.setEvent(OnBoardingEvent.OnSlideRight)
            }
        )
    }
}

@Composable
fun OnBoardingContent(
    modifier: Modifier = Modifier,
    ui: OnBoardingUi,
    onNextClick: () -> Unit = {},
    onPreviousClick: () -> Unit = {},
    onSlideLeft: () -> Unit = {},
    onSlideRight: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = ui.illustration),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = ui.headline),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(40.dp))
        Slider(ui = ui)
        Spacer(modifier = Modifier.weight(1F))
        PalButton(
            modifier = Modifier.fillMaxWidth(),
            styles = ButtonStyles.Secondary,
            text = stringResource(id = ui.textButton),
            iconEnd = R.drawable.ic_arrow_right,
            onClick = onNextClick
        )
        AnimatedVisibility(visible = ui.currentPage != 0) {
            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .clickable {
                        onPreviousClick()
                    },
                text = stringResource(id = R.string.previous),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Composable
fun Slider(
    modifier: Modifier = Modifier,
    ui: OnBoardingUi
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
       onBoardingUis.forEachIndexed { index, slide ->
           DotSlider(selected = index == ui.currentPage)
       }
    }
}

@Composable
fun DotSlider(
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    Box(
        modifier = modifier
            .size(12.dp)
            .background(
                if (selected) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOnBoardingContent() {
    NotePalTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            OnBoardingContent(ui = OnBoardingUi())
        }
    }
}