package io.github.livenote.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.livenote.databinding.FragmentMainBinding
import io.github.livenote.ui.adapter.NoteAdapter
import io.github.livenote.ui.viewmodel.MainFragmentViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import io.github.livenote.R

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModels()

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.addRecyclerView.layoutManager = layoutManager

        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        binding.addRecyclerView.addItemDecoration(decoration)

        val noteAdapter = NoteAdapter()
        noteAdapter.clickFunction = { note ->
            val action = MainFragmentDirections.actionMainFragmentToAddViewNoteFragment(
                noteName = note.name,
                noteContent = note.content,
            )
            findNavController().navigate(action)
        }
        binding.addRecyclerView.adapter = noteAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        setRecyclerView()
        binding.addNoteFab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addViewNoteFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.notes.collect {
                    ((binding.addRecyclerView.adapter) as NoteAdapter).submitList(it)
                    Log.d("MAIN_FRAGMENT", "Successful set notes in adapter" + ", notes: "
                    + it.toString())
                }
            }
        }
    }
}