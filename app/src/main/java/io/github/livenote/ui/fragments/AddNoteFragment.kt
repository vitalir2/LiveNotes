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
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.github.livenote.R
import io.github.livenote.data.models.Note
import io.github.livenote.databinding.FragmentAddNoteBinding
import io.github.livenote.ui.viewmodel.AddNoteFragmentViewModel
import io.github.livenote.ui.viewmodel.MainFragmentViewModel
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private val viewModel: AddNoteFragmentViewModel by viewModels()
    private val arguments: AddNoteFragmentArgs by navArgs()
    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
    private lateinit var currentDate: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        currentDate =  SimpleDateFormat("dd:M:yyyy", Locale.getDefault()).format(Date())
        binding.contentTextInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus &&  binding.contentTextInput.text.isNotBlank()
                && binding.titleTextInput.text.isNotBlank()) {
                viewModel.insertNote(
                    Note(
                        name = binding.titleTextInput.text.toString(),
                        content = binding.contentTextInput.text.toString(),
                        date = currentDate,
                    )
                )
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments.noteName != " " && arguments.noteContent != " ")
          binding.titleTextInput.text = arguments.noteName.toEditable()
          binding.contentTextInput.text = arguments.noteContent.toEditable()
      }
    }