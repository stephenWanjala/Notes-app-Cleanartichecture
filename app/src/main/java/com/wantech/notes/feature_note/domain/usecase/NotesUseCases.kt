package com.wantech.notes.feature_note.domain.usecase

data class NotesUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val addNotesUseCase: CreateNoteUseCase,
    val getNoteUseCase:GetNoteUseCase
)
