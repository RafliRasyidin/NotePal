package com.rasyidin.notepal.domain.model.detail_note

import com.rasyidin.notepal.data.local.entities.note.NoteEntity

data class Note(
    val id: String = "",
    val title: String = "",
    val updatedAt: String = "",
    val createdAt: String = "",
    val reminder: String = "",
    val pinned: Boolean = false,
    val finished: Boolean = false,
    val backgroundColor: Int = -1,
    val containerColor: Int = -1,
    val onContainerColor: Int = -1,
    var notesContent: List<NoteContent> = emptyList(),
    var tags: List<Tag> = emptyList(),
    var listTask: List<Task> = emptyList(),
    val showSheetMenuExtra: Boolean = false,
) {
    fun toEntity() = NoteEntity(
        id = id,
        title = title,
        updatedAt = updatedAt,
        createdAt = createdAt,
        reminder = reminder,
        pinned = if (pinned) 1 else 0,
        finished = if (finished) 1 else 0,
        backgroundColor = backgroundColor,
        onContainerColor = onContainerColor,
        containerColor = containerColor
    )
}
