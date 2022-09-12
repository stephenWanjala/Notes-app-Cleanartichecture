package com.wantech.notes.feature_note.domain.usecase

import com.wantech.notes.feature_note.domain.model.Note
import com.wantech.notes.feature_note.domain.repository.NoteRepository
import com.wantech.notes.feature_note.domain.util.NoteOrder
import com.wantech.notes.feature_note.domain.util.OrderType
import com.wantech.notes.feature_note.domain.util.OrderType.Descending
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder
        = NoteOrder.Date(Descending)
    ): Flow<List<Note>> = repository.getNotes()
        .map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Tittle -> notes.sortedByDescending { it.tittle.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timeStamp }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Tittle -> notes.sortedBy { it.tittle.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timeStamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }
            }
        }
}