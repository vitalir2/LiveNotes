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
    override fun insert(note: NoteDb) {
        return noteDao.insert(note)
    }

    override fun delete(note: NoteDb) {
        return noteDao.delete(note)
    }

    override fun getAll(): Flow<List<Note>> {
        return noteDao.getAll().map {notes ->
            notes.map {note ->
                Converters.convertNoteDbToNote(note)}}
    }
}