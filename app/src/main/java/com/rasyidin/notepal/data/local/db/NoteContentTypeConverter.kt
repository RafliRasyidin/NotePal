package com.rasyidin.notepal.data.local.db

import androidx.room.TypeConverter
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentType
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentType.FreeText
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentType.Image

class NoteContentTypeConverter {

    @TypeConverter
    fun toContentType(value: String): NoteContentType {
        return when (value) {
            Image.name -> Image
            FreeText.name -> FreeText
            else -> throw IllegalStateException("Illegal content type, expected ContentType name but founded $value")
        }
    }

    @TypeConverter
    fun fromContentType(type: NoteContentType): String {
        return when (type) {
            Image -> Image.name
            FreeText -> FreeText.name
        }
    }
}