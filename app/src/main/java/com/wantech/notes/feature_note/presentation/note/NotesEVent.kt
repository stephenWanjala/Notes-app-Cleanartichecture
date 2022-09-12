package com.wantech.notes.feature_note.presentation.note

import com.wantech.notes.feature_note.domain.model.Note
import com.wantech.notes.feature_note.domain.util.NoteOrder

sealed class NotesEVent {
    data class Order(val noteOrder: NoteOrder) : NotesEVent()
    data class DeleteNote(val note: Note) : NotesEVent()
    object RestoreNote : NotesEVent()
    object ToggleOrderSection : NotesEVent()

}
