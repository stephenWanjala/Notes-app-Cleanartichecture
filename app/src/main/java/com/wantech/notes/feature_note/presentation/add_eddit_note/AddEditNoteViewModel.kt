package com.wantech.notes.feature_note.presentation.add_eddit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.wantech.notes.feature_note.domain.model.Note
import com.wantech.notes.feature_note.domain.usecase.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel() {
    private var _noteTittle = mutableStateOf<NoteTextFieldState>(
        NoteTextFieldState(
            hint = "Enter Tittle"
        )
    )
    val noteTittle: State<NoteTextFieldState> = _noteTittle

    private var _noteContent = mutableStateOf<NoteTextFieldState>(
        NoteTextFieldState(
            hint = "Enter some Content"
        )
    )
    val noteContent: State<NoteTextFieldState> = _noteContent
    private var _noteColor = mutableStateOf<Int>(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventflow = _eventFlow.asSharedFlow()


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }


}