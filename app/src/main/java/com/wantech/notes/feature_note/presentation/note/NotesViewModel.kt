package com.wantech.notes.feature_note.presentation.note

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.notes.feature_note.domain.model.Note
import com.wantech.notes.feature_note.domain.usecase.NotesUseCases
import com.wantech.notes.feature_note.domain.util.NoteOrder
import com.wantech.notes.feature_note.domain.util.OrderType
import com.wantech.notes.feature_note.presentation.note.NotesEVent
import com.wantech.notes.feature_note.presentation.note.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel() {


    init {

        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    private var _state = mutableStateOf<NotesState>(NotesState())
    val state: MutableState<NotesState> = _state
    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null
    fun onEvent(eVent: NotesEVent) {
        when (eVent) {
            is NotesEVent.Order -> {
                if (state.value.noteOrder::class == eVent.noteOrder::class
                    && state.value.noteOrder.orderType == eVent.noteOrder.orderType
                ) {
                    return
                }
                getNotes(eVent.noteOrder)


            }

            is NotesEVent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNoteUseCase(eVent.note)
                    recentlyDeletedNote = eVent.note
                }
            }

            is NotesEVent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOderSectionVisible = !state.value.isOderSectionVisible
                )

            }

            is NotesEVent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNotesUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()

        getNotesJob = notesUseCases.getNotesUseCase(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder,
                )

            }.launchIn(viewModelScope)
    }

}
