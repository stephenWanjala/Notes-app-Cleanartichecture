package com.wantech.notes.feature_note.domain.usecase

import com.wantech.notes.feature_note.domain.model.Note
import com.wantech.notes.feature_note.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? =
        repository.getNoteById(id)
}