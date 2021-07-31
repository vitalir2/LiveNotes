package io.github.livenote.data.repository

import io.github.livenote.data.db.NoteDao
import io.github.livenote.data.models.Note
import io.github.livenote.data.models.NoteDb
import io.github.livenote.utils.Converters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
    ) : NotesRepository {
    override suspend fun insert(note: Note) {
        return noteDao.insert(Converters.convertNoteToNoteDb(note))
    }

    override suspend fun delete(note: Note) {
        return noteDao.delete(Converters.convertNoteToNoteDb(note))
    }

    override fun getAll(): Flow<List<Note>> {
        return noteDao.getAll().map {notes ->
            notes.map {note ->
                Converters.convertNoteDbToNote(note)}}
    }
}