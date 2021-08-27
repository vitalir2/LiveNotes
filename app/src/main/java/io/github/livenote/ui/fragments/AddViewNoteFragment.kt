package io.github.livenote.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.github.livenote.data.models.Note
import io.github.livenote.databinding.FragmentAddViewNoteBinding
import io.github.livenote.ui.viewmodel.AddViewNoteFragmentViewModel
import io.github.livenote.utils.Extensions.toEditable
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddViewNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddViewNoteBinding
    private val viewModel: AddViewNoteFragmentViewModel by viewModels()
    private val arguments: AddViewNoteFragmentArgs by navArgs()
    private lateinit var currentDate: String
    private var oldName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddViewNoteBinding.inflate(inflater, container, false)
        currentDate = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault()).format(Date())
        binding.contentTextInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && binding.contentTextInput.text.isNotBlank()
                && binding.titleTextInput.text.isNotBlank()
            ) {
                viewModel.insertNoteDeleteOld(
                    Note(
                        name = binding.titleTextInput.text.toString(),
                        content = binding.contentTextInput.text.toString(),
                        date = currentDate,
                    ),
                    oldName,
                )
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        oldName = arguments.noteName
        if (arguments.noteName.isNotBlank() && arguments.noteContent.isNotBlank()) {
            binding.titleTextInput.text = arguments.noteName.toEditable()
            binding.contentTextInput.text = arguments.noteContent.toEditable()
        }
    }
}