package com.wantech.notes.feature_note.presentation.add_eddit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnterTittle(val value: String) : AddEditNoteEvent()
    data class ChangeTittleFocusState(val focusState: FocusState) : AddEditNoteEvent()
    data class ChangeContentFocusState(val focusState: FocusState) : AddEditNoteEvent()
    data class EnterContent(val value: String) : AddEditNoteEvent()
    data class ChangeColor(val color: Int) : AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()


}
