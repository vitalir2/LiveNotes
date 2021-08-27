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
    fun insertNoteDeleteOld(note: Note, oldName: String) {
        viewModelScope.launch {
            if (oldName != note.name) {
                notesRepository.delete(Note(oldName, "", ""))
            }
            notesRepository.insert(note)
        }
    }
}