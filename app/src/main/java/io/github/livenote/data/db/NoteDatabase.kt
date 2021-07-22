package io.github.livenote.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.livenote.data.models.NoteDb

@Database(version = 1, entities = [NoteDb::class])
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}