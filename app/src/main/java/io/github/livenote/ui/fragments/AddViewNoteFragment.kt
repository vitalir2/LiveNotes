package io.github.livenote.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.github.livenote.R
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

    private var actionMode: ActionMode? = null
    private val saveNote = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            val inflater = mode?.menuInflater
            inflater?.inflate(R.menu.top_app_bar, menu)
            return true
        }

        override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
            return true
        }

        override fun onActionItemClicked(p0: ActionMode?, p1: MenuItem?): Boolean {
            return true
        }

        override fun onDestroyActionMode(p0: ActionMode?) {
            actionMode = null
            if (binding.titleTextInput.text.isNotBlank()
                && binding.contentTextInput.text.isNotBlank()) {
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

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddViewNoteBinding.inflate(inflater, container, false)
        currentDate = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault()).format(Date())
        binding.contentTextInput.setOnFocusChangeListener { _, hasFocus ->
            actionOnFocus(hasFocus)
        }
        binding.titleTextInput.setOnFocusChangeListener { _, hasFocus ->
            actionOnFocus(hasFocus)
        }
        return binding.root
    }

    private fun actionOnFocus(hasFocus: Boolean) {
        if (hasFocus) {
            when(actionMode) {
                null -> {
                    actionMode = activity?.startActionMode(saveNote)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        oldName = arguments.noteName
        if (arguments.noteName.isNotBlank() && arguments.noteContent.isNotBlank()) {
            binding.titleTextInput.text = arguments.noteName.toEditable()
            binding.contentTextInput.text = arguments.noteContent.toEditable()
        }
    }
}