package com.rasyidin.notepal.ui.screen.finished

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.R
import com.rasyidin.notepal.ui.component.NegativeState
import com.rasyidin.notepal.ui.theme.NotePalTheme

@Composable
fun FinishedContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            NegativeState(
                modifier = Modifier.align(Alignment.Center),
                image = R.drawable.illustration_6,
                title = stringResource(id = R.string.no_finished_notes_yet),
                description = stringResource(id = R.string.no_finished_notes_yet_description)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFinishedContent() {
    NotePalTheme {
        FinishedContent()
    }
}