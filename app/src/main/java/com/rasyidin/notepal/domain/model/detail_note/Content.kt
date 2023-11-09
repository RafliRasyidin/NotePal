package com.rasyidin.notepal.domain.model.detail_note

data class Content(
    val type: ContentType = ContentType.FreeText,
    var noteContent: NoteContent? = null,
    var task: Task? = null
)

enum class ContentType {
    FreeText,
    Image,
    Task
}
