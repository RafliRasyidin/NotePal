package com.rasyidin.notepal.domain.model.home

import androidx.compose.ui.graphics.Color
import com.rasyidin.notepal.domain.model.add_notes.NoteType

data class Note(
    val idNote: String,
    val title: String,
    val content: String,
    val image: String?,
    val colors: NoteColor,
    val type: NoteType
)

data class NoteColor(
    val background: Color,
    val container: Color,
    val onContainer: Color
)
