package io.github.livenote.data.repository

import io.github.livenote.data.models.Note
import io.github.livenote.data.models.NoteDb
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun insert(note: NoteDb)
    fun delete(note: NoteDb)
    fun getAll(note: NoteDb): Flow<List<Note>>
}