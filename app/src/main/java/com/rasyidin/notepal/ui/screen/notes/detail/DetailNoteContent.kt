package com.rasyidin.notepal.ui.screen.notes.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rasyidin.notepal.ui.theme.NotePalTheme

@Composable
fun DetailNoteContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetailNoteContent() {
    NotePalTheme {
        DetailNoteContent()
    }
}