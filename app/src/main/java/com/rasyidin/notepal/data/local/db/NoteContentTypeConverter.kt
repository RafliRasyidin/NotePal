package com.rasyidin.notepal.data.local.db

import androidx.room.TypeConverter
import com.rasyidin.notepal.data.local.entities.note_content.ContentType
import com.rasyidin.notepal.data.local.entities.note_content.ContentType.Description
import com.rasyidin.notepal.data.local.entities.note_content.ContentType.Image

class NoteContentTypeConverter {

    @TypeConverter
    fun toContentType(value: String): ContentType {
        return when (value) {
            Image.name -> Image
            Description.name -> Description
            else -> throw IllegalStateException("Illegal content type, expected ContentType name but founded $value")
        }
    }

    @TypeConverter
    fun fromContentType(type: ContentType): String {
        return when (type) {
            Image -> Image.name
            Description -> Description.name
        }
    }
}