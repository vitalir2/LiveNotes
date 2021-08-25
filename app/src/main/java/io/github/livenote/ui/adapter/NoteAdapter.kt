package io.github.livenote.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.livenote.data.models.Note
import io.github.livenote.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class NoteDiffCall : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}

class NoteAdapter : ListAdapter<Note, NoteAdapter.ItemViewHolder>(NoteDiffCall()) {

    var clickFunction: ((Note) -> Unit)? = null

    inner class ItemViewHolder(private val binding: ItemNoteBinding)
        : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                clickFunction?.invoke(currentList[adapterPosition])
            }
        }
            fun bind(note: Note) {
                binding.titleText.text = note.name
                binding.dateText.text = note.date
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