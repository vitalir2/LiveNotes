package io.github.livenote.utils

import io.github.livenote.data.models.Note
import io.github.livenote.data.models.NoteDb

object Converters {
    fun convertNoteDbToNote(noteDb: NoteDb): Note {
        return Note(noteDb.name, noteDb.content)
    }

    fun convertNoteToNoteDb(note: Note): NoteDb {
        return NoteDb(note.name, note.content)
    }
}