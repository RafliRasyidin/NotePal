package com.rasyidin.notepal.domain.model.detail_note

import com.rasyidin.notepal.data.local.entities.note_content.ContentType
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentEntity

data class NoteContent(
    val id: String,
    var idNote: String,
    var content: String,
    var orderContent: Int,
    var type: ContentType
) {
    fun toEntity() = NoteContentEntity(
        id = id,
        idNote = idNote,
        content = content,
        orderContent = orderContent,
        type = type
    )
}
