package io.github.livenote.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.livenote.data.models.Note
import io.github.livenote.R
import io.github.livenote.databinding.ItemNoteBinding

class ItemAdapter(
    private var dataset: List<Note>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val binding: ItemNoteBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(note: Note) {
                binding.titleText.text = note.name
                binding.dateText.text = "01.01.2020"
            }
    }

    fun setList(list: List<Note>) {
        dataset = list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataset.size
}