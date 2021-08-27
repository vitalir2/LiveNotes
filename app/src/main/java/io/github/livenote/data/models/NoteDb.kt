package io.github.livenote.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteDb(
    @PrimaryKey val name: String,
    val date: String,
    val content: String,
)
