package com.rasyidin.notepal.domain.model.detail_note

import com.rasyidin.notepal.data.local.entities.tag.TagEntity

data class Tag(
    val id: String,
    var idNote: String,
    var tag: String,
) {
    fun toEntity() = TagEntity(
        id = id,
        idNote = idNote,
        tag = tag
    )
}
