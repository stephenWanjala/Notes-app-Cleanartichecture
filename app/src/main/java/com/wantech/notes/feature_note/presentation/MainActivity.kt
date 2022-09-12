package com.wantech.notes.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.wantech.notes.feature_note.domain.util.NoteOrder
import com.wantech.notes.feature_note.presentation.note.components.NotesScreen
import com.wantech.notes.feature_note.presentation.note.components.OrderSection
import com.wantech.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                val navController = rememberNavController()
                NotesScreen(navController = navController )
            }
        }
    }
}

