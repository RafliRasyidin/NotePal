package com.rasyidin.notepal.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rasyidin.notepal.ui.theme.NotePalTheme

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.onPrimary
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {
    NotePalTheme {
        HomeContent()
    }
}