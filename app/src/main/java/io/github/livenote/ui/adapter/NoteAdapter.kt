package io.github.livenote.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.livenote.data.models.Note
import io.github.livenote.databinding.ItemNoteBinding

class NoteDiffCall : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}

class NoteAdapter : ListAdapter<Note, NoteAdapter.ItemViewHolder>(NoteDiffCall()) {

    class ItemViewHolder(private val binding: ItemNoteBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(note: Note) {
                binding.titleText.text = note.name
                binding.dateText.text = "01.01.2020"
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}