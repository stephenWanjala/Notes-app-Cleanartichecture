package com.wantech.notes.feature_note.presentation.note

import com.wantech.notes.feature_note.domain.model.Note
import com.wantech.notes.feature_note.domain.util.NoteOrder
import com.wantech.notes.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOderSectionVisible:Boolean =false
)

