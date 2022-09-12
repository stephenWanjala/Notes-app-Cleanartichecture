package com.wantech.notes.feature_note.domain.usecase

import com.wantech.notes.feature_note.domain.model.InvalidNoteException
import com.wantech.notes.feature_note.domain.model.Note
import com.wantech.notes.feature_note.domain.repository.NoteRepository

data class CreateNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.tittle.isBlank()) {
            throw InvalidNoteException("The tittle of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw  InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }

}
