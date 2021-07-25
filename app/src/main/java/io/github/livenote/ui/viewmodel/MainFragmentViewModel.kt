package io.github.livenote.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.livenote.data.repository.NotesRepository
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel
@Inject constructor(
    private val notesRepository: NotesRepository
    ) : ViewModel() {
}