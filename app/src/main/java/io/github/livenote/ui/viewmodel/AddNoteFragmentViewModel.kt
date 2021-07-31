package io.github.livenote.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.livenote.data.models.Note
import io.github.livenote.data.repository.NotesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteFragmentViewModel
@Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {

    fun insertNote(note: Note) {
        viewModelScope.launch {
            notesRepository.delete(note)
            notesRepository.insert(note)
        }
    }
}