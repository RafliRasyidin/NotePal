package com.rasyidin.notepal.di

import com.rasyidin.notepal.domain.usecase.detail_note.DetailNoteUseCase
import com.rasyidin.notepal.domain.usecase.detail_note.DetailNoteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun providesDetailNoteUseCase(detailNoteUseCase: DetailNoteUseCaseImpl): DetailNoteUseCase
}