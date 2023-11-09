package com.rasyidin.notepal.domain.model.detail_note

import com.rasyidin.notepal.data.local.entities.note_content.NoteContentEntity
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentType

data class NoteContent(
    val id: String,
    var idNote: String,
    var value: String,
    var orderContent: Int,
    var type: NoteContentType
) {
    fun toEntity() = NoteContentEntity(
        id = id,
        idNote = idNote,
        content = value,
        orderContent = orderContent,
        type = type
    )
}
