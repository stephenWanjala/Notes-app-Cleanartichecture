package com.wantech.notes.feature_note.presentation.add_eddit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.notes.feature_note.domain.model.InvalidNoteException
import com.wantech.notes.feature_note.domain.model.Note
import com.wantech.notes.feature_note.domain.usecase.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    notesUseCases.getNoteUseCase(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTittle.value = noteTittle.value.copy(
                            text = note.tittle,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

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

    private var currentNoteId: Int? = null
    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        notesUseCases.addNotesUseCase(
                            Note(
                                tittle = noteTittle.value.text,
                                content = noteContent.value.text,
                                timeStamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(message = e.message ?: "could'nt save note")
                        )
                    }
                }
            }
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditNoteEvent.ChangeContentFocusState -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeTittleFocusState -> {
                _noteTittle.value = noteTittle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTittle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnterContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.EnterTittle -> {
                _noteTittle.value = noteTittle.value.copy(
                    text = event.value
                )
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }


}