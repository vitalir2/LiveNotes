package io.github.livenote.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.livenote.data.models.NoteDb
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    fun insert(note: NoteDb)

    @Delete
    fun delete(note: NoteDb)

    @Query("SELECT * FROM notedb")
    fun getAll(): Flow<List<NoteDb>>
}