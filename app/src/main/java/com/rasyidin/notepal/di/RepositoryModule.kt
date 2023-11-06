package com.rasyidin.notepal.di

import com.rasyidin.notepal.data.repository.NoteRepository
import com.rasyidin.notepal.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesNoteRepository(repository: NoteRepositoryImpl): NoteRepository
}