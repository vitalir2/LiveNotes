package io.github.livenote.utils

import io.github.livenote.data.models.Note
import io.github.livenote.data.models.NoteDb

object Converters {
    fun convertNoteDbToNote(noteDb: NoteDb): Note {
        return Note(
            name = noteDb.name,
            date = noteDb.date,
            content = noteDb.content,
        )
    }

    fun convertNoteToNoteDb(note: Note): NoteDb {
        return NoteDb(
            name = note.name,
            date = note.date,
            content = note.content,
        )
    }
}