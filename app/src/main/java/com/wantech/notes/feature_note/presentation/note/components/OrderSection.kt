package com.wantech.notes.feature_note.presentation.note.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wantech.notes.feature_note.domain.util.NoteOrder
import com.wantech.notes.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {


    Column(
        modifier = Modifier,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Tittle",
                selected = noteOrder is NoteOrder.Tittle,
                onSelected = { /*TODO*/ })
        }
    }
}