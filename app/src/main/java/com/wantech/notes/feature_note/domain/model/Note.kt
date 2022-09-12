package com.wantech.notes.feature_note.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    val tittle: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int?
) {
    companion object {
        val noteColors = listOf(
            Color.Blue,
            Color.Yellow,
            Color.Cyan,
            Color.Magenta,
            Color.Green,
            Color.LightGray
        )
    }
}

class InvalidNoteException(message: String) : Exception(message)