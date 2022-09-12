package com.wantech.notes.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wantech.notes.feature_note.domain.util.NoteOrder
import com.wantech.notes.feature_note.presentation.note.components.OrderSection
import com.wantech.notes.ui.theme.NotesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {

            }
        }
    }
}

