package io.github.livenote.data.repository

import io.github.livenote.data.models.Note
import io.github.livenote.data.models.NoteDb
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insert(note: Note)
    suspend fun delete(note: Note)
    suspend fun getByName(name: String): Note?
    fun getAll(): Flow<List<Note>>
    fun getSearchResultStream(query: String): Flow<List<Note>>
}