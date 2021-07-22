package io.github.livenote.di

import android.content.Context
import androidx.room.Room
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.livenote.data.db.NoteDao
import io.github.livenote.data.db.NoteDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context): NoteDatabase {
        return Room.databaseBuilder(
            appContext,
            NoteDatabase::class.java,
            "note.db"
        ).fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    @Provides
    fun provideNoteDao(database: NoteDatabase): NoteDao {
        return database.noteDao()
    }
}