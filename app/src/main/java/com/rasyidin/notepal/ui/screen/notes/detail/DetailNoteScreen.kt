package com.rasyidin.notepal.ui.screen.notes.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rasyidin.notepal.ui.screen.notes.detail.free_notes.FreeNotesContent

@Composable
fun DetailNoteScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    FreeNotesContent(
        modifier = modifier,
        onBackClick = onBackClick,
        onBookmarkClick = {},
        onSearchClick = {},
        onMenuClick = {}
    )
}