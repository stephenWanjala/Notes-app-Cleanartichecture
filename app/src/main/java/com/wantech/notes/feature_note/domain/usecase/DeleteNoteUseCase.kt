package com.wantech.notes.feature_note.domain.usecase

import com.wantech.notes.feature_note.domain.model.Note
import com.wantech.notes.feature_note.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) = repository.deleteNote(note)
}