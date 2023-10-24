package com.rasyidin.notepal.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rasyidin.notepal.R
import com.rasyidin.notepal.ui.component.NegativeState
import com.rasyidin.notepal.ui.theme.NotePalTheme

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            NegativeState(
                modifier = Modifier.align(Alignment.Center),
                image = R.drawable.illustration_5,
                title = stringResource(id = R.string.start_your_journey),
                description = stringResource(id = R.string.start_your_journey_description)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {
    NotePalTheme {
        HomeContent()
    }
}