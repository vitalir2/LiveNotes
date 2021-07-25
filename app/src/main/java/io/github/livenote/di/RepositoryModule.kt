package io.github.livenote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.livenote.data.repository.NotesRepository
import io.github.livenote.data.repository.NotesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract  class RepositoryModule{
    @Binds
    abstract fun bindNotesRepository(
        notesRepositoryImpl: NotesRepositoryImpl
    ): NotesRepository
}