package com.rasyidin.notepal.ui.screen.notes.add

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rasyidin.notepal.domain.model.add_notes.AddCardNoteModel

@Composable
fun AddNotesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNoteTypeClick: (AddCardNoteModel) -> Unit
) {
    AddNotesContent(
        modifier = modifier,
        onBackClick = onBackClick,
        onNoteTypeClick = onNoteTypeClick
    )
}