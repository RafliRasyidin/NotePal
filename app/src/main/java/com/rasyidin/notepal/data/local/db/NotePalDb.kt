package com.rasyidin.notepal.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rasyidin.notepal.data.local.entities.note.NoteDao
import com.rasyidin.notepal.data.local.entities.note.NoteEntity
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentDao
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentEntity
import com.rasyidin.notepal.data.local.entities.tag.TagDao
import com.rasyidin.notepal.data.local.entities.tag.TagEntity
import com.rasyidin.notepal.data.local.entities.task.TaskDao
import com.rasyidin.notepal.data.local.entities.task.TaskEntity

@Database(
    entities = [
        NoteEntity::class,
        NoteContentEntity::class,
        TagEntity::class,
        TaskEntity::class
    ],
    version = NotePalDb.VERSION_DB,
)
@TypeConverters(NoteContentTypeConverter::class)
abstract class NotePalDb : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun noteContentDao(): NoteContentDao
    abstract fun tagDao(): TagDao
    abstract fun taskDao(): TaskDao

    companion object {
        const val VERSION_DB = 1
        const val DATABASE_NAME = "note_pal.db"
    }
}