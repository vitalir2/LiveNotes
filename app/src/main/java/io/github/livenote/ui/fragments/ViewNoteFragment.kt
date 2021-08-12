package io.github.livenote.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import io.github.livenote.R
import io.github.livenote.databinding.FragmentMainBinding
import io.github.livenote.databinding.FragmentViewNoteBinding

class ViewNoteFragment : Fragment() {

    private lateinit var binding: FragmentViewNoteBinding

    private val arguments: ViewNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.titleTv.text = arguments.noteName
        binding.contentTv.text = arguments.noteContent
    }
}