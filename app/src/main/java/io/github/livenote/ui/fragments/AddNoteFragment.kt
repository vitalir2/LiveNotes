package io.github.livenote.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.livenote.R
import io.github.livenote.data.models.Note
import io.github.livenote.databinding.FragmentAddNoteBinding
import io.github.livenote.databinding.FragmentViewNoteBinding
import io.github.livenote.ui.viewmodel.AddNoteFragmentViewModel
import io.github.livenote.ui.viewmodel.MainFragmentViewModel

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private val viewModel: AddNoteFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        binding.contentTextInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus &&  binding.contentTextInput.text.isNotBlank()
                && binding.titleTextInput.text.isNotBlank()) {
                viewModel.insertNote(
                    Note(
                        name = binding.titleTextInput.text.toString(),
                        content = binding.contentTextInput.text.toString(),
                    )
                )
            }
        }
        return binding.root
    }
}