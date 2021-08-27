package io.github.livenote.data.db

import androidx.room.*
import io.github.livenote.data.models.NoteDb
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteDb)

    @Delete
    suspend fun delete(note: NoteDb)

    @Query("SELECT * FROM notedb WHERE name == :name")
    suspend fun getByName(name: String): NoteDb?

    @Query("SELECT * FROM notedb")
    fun getAll(): Flow<List<NoteDb>>
}