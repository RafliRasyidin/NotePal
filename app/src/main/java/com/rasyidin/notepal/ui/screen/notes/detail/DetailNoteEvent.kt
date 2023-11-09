package com.rasyidin.notepal.ui.screen.notes.detail

import androidx.compose.ui.graphics.Color
import com.rasyidin.notepal.domain.model.detail_note.Content
import com.rasyidin.notepal.domain.model.detail_note.Tag

sealed interface DetailNoteEvent {
    data class OnTitleChange(val title: String) : DetailNoteEvent
    data class OnContentChange(val notesContent: List<Content>) : DetailNoteEvent
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
    data object UpdateNote : DetailNoteEvent
    data object DeleteNote : DetailNoteEvent
    data object AddFreeText : DetailNoteEvent
    data object AddChecklist : DetailNoteEvent
    data object AddImage : DetailNoteEvent
    data object OnFirstTime : DetailNoteEvent
}
