package com.rasyidin.notepal.di

import android.content.Context
import androidx.room.Room
import com.rasyidin.notepal.data.local.db.NotePalDb
import com.rasyidin.notepal.data.local.entities.note.NoteDao
import com.rasyidin.notepal.data.local.entities.note_content.NoteContentDao
import com.rasyidin.notepal.data.local.entities.tag.TagDao
import com.rasyidin.notepal.data.local.entities.task.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesNotePalDb(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        NotePalDb::class.java,
        NotePalDb.DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesNoteDao(db: NotePalDb): NoteDao = db.noteDao()

    @Provides
    @Singleton
    fun providesNoteContentDao(db: NotePalDb): NoteContentDao = db.noteContentDao()

    @Provides
    @Singleton
    fun providesTagDao(db: NotePalDb): TagDao = db.tagDao()

    @Provides
    @Singleton
    fun providesTaskDao(db: NotePalDb): TaskDao = db.taskDao()
}