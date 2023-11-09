package com.rasyidin.notepal.data.local.entities.note_content

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_content")
data class NoteContentEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("idNote")
    val idNote: String,

    @ColumnInfo("content")
    val content: String,

    @ColumnInfo("orderContent")
    val orderContent: Int,

    @ColumnInfo("content_type")
    val type: NoteContentType
)

enum class NoteContentType {
    Image, FreeText
}
