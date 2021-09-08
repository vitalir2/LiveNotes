package io.github.livenote.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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
    private val adapter = NoteAdapter()
    private val viewModel: MainFragmentViewModel by viewModels()

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        binding.addRecyclerView.layoutManager = layoutManager

        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        binding.addRecyclerView.addItemDecoration(decoration)

        adapter.clickFunction = { note ->
            val action = MainFragmentDirections.actionMainFragmentToAddViewNoteFragment(
                noteName = note.name,
                noteContent = note.content,
            )
            findNavController().navigate(action)
        }
        binding.addRecyclerView.adapter = adapter
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        if (newText.isNotBlank()) {
                            viewModel.searchNotes(newText).collect {
                                adapter.submitList(it)
                            }
                        } else {
                            viewModel.notes.collect {
                                adapter.submitList(it)
                            }
                        }
                    }
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        if (query.isNotBlank()) {
                            viewModel.searchNotes(query).collect {
                                adapter.submitList(it)
                            }
                        } else {
                            viewModel.notes.collect {
                                adapter.submitList(it)
                            }
                        }
                    }
                }
                return true
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setRecyclerView()
        setSearchView()

        binding.addNoteFab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addViewNoteFragment)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.notes.collect {
                    (adapter.submitList(it))
                    Log.d("MAIN_FRAGMENT", "Successful set notes in adapter" + ", notes: "
                    + it.toString())
                }
            }
        }
    }
}