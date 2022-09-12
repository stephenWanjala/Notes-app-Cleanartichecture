package com.wantech.notes.feature_note.presentation.note.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wantech.notes.feature_note.presentation.note.NotesViewModel

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel(),

) {
    val state =viewModel.state
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    
    Scaffold(floatingActionButton = { 
        FloatingActionButton(onClick = { /*TODO*/ }) {
            
        }
    }) {
        
    }

}