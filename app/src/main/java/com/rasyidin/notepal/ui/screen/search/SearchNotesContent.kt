package com.rasyidin.notepal.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rasyidin.notepal.R
import com.rasyidin.notepal.ui.component.NegativeState
import com.rasyidin.notepal.ui.component.SearchBox
import com.rasyidin.notepal.ui.theme.NotePalTheme

@Composable
fun SearchNotesContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var text by remember { mutableStateOf("") }
        Column {
            SearchSection(
                query = text,
                onQueryChange = { newText ->
                    text = newText
                },
                onSearch = { submittedText ->

                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F),
                contentAlignment = Alignment.Center
            ) {
                NegativeState(
                    modifier = Modifier.align(Alignment.Center),
                    image = R.drawable.illustration_6,
                    title = stringResource(id = R.string.not_found),
                    description = stringResource(id = R.string.not_found_description, text)
                )
            }
        }
    }
}

@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        SearchBox(
            modifier = Modifier.padding(horizontal = 16.dp),
            query = query,
            hint = stringResource(id = R.string.hint_search),
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            onFocusChange = onFocusChange
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        )
    }
}

@Preview
@Composable
private fun PreviewSearchNotesContent() {
    NotePalTheme {
        SearchNotesContent()
    }
}