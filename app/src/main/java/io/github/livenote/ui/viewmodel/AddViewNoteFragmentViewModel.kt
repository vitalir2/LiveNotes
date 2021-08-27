package io.github.livenote.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.livenote.data.models.Note
import io.github.livenote.data.repository.NotesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewNoteFragmentViewModel
@Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {
    fun insertNote(note: Note) {
        viewModelScope.launch {
            val dbNote = notesRepository.getByName(note.name)
            if (dbNote != null) {
                notesRepository.delete(note)
            }
            notesRepository.insert(note)
        }
    }
}