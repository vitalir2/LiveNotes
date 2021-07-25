package io.github.livenote.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.livenote.data.models.Note
import io.github.livenote.data.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel
@Inject constructor(
    private val notesRepository: NotesRepository
    ) : ViewModel() {
    private val _notes = MutableStateFlow(emptyList<Note>())
    val notes: StateFlow<List<Note>> = _notes

    init {
        viewModelScope.launch {
            notesRepository.getAll()
                .collect { newNotes ->
                    _notes.value = newNotes
                }
        }
    }
}