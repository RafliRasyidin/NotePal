package com.rasyidin.notepal.domain.model.detail_note

import com.rasyidin.notepal.data.local.entities.note.NoteEntity

data class DetailNote(
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
    var tags: List<Tag> = emptyList(),
    val showSheetMenuExtra: Boolean = false,
    val contents: List<Content> = emptyList()
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
