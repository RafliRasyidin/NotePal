package com.rasyidin.notepal.ui.screen.notes.detail

import androidx.compose.ui.graphics.Color
import com.rasyidin.notepal.domain.model.detail_note.NoteContent
import com.rasyidin.notepal.domain.model.detail_note.Tag
import com.rasyidin.notepal.domain.model.detail_note.Task

sealed interface DetailNoteEvent {
    data class OnTitleChange(val title: String) : DetailNoteEvent
    data class OnContentChange(val notesContent: List<NoteContent>) : DetailNoteEvent
    data class OnTaskChange(val tasks: List<Task>) : DetailNoteEvent
    data class OnTagChange(val tags: List<Tag>) : DetailNoteEvent
    data class OnPinned(val pinned: Boolean) : DetailNoteEvent
    data class OnReminderChange(val reminder: String) : DetailNoteEvent
    data class OnFinished(val finished: Boolean) : DetailNoteEvent
    data class OnColorChange(
        val backgroundColor: Color,
        val containerColor: Color,
        val onContainerColor: Color,
    ) : DetailNoteEvent
    data object ShowSheetMenuExtra : DetailNoteEvent
    data object HideSheet : DetailNoteEvent
    data object InsertNote : DetailNoteEvent
    data object UpdateNote : DetailNoteEvent
    data object DeleteNote : DetailNoteEvent

}
