package io.github.livenote.data.repository

import io.github.livenote.data.models.Note
import io.github.livenote.data.models.NoteDb
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insert(note: Note)
    suspend fun delete(note: Note)
    fun getAll(): Flow<List<Note>>
}