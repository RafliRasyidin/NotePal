package com.rasyidin.notepal.domain.model.home

import com.rasyidin.notepal.domain.model.add_notes.NoteType

data class CategoricalNote(
    val noteType: NoteType,
    val notes: List<Note>
)
