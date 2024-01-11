package com.example.notes_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes_app.Screen.Note
import com.example.notes_app.Screen.Screen
import com.example.notes_app.Screen.NoteCreate
import com.example.notes_app.Screen.NoteDetail
import com.example.notes_app.Screen.NoteEdit
import com.example.notes_app.Screen.OverviewScreen
import com.example.notes_app.ui.theme.NotesappTheme
import java.net.URLDecoder
import java.net.URLEncoder

// Navhost code from Garrets Workshop videos
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var notes by remember { mutableStateOf(listOf<Note>()) }

            fun addNote(note: Note) {
                notes = notes + note
            }

            fun deleteNote(note: Note) {
                notes = notes.filter { it != note }
            }

            NotesappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Screen.Home.route) {
                        composable(route = Screen.Home.route) {
                            OverviewScreen(navController = navController, notes = notes, deleteNote = ::deleteNote, editNote = { note ->
                                navController.navigate("${Screen.NoteEdit.route}/${URLEncoder.encode(note.title, "UTF-8")}/${URLEncoder.encode(note.description, "UTF-8")}")
                            })
                        }
                        composable(route = "${Screen.Detail.route}/{title}/{description}") { backStackEntry ->
                            val arguments = backStackEntry.arguments
                            val title = arguments?.getString("title")?.let { URLDecoder.decode(it, "UTF-8") } ?: ""
                            val description = arguments?.getString("description")?.let { URLDecoder.decode(it, "UTF-8") } ?: ""
                            NoteDetail(navController, title, description)
                        }
                        composable( route = Screen.NoteCreate.route) {
                            NoteCreate(navController = navController) { title, description ->
                                addNote(Note(title, description))
                            }
                        }
                        composable(route = "${Screen.NoteEdit.route}/{title}/{description}") { backStackEntry ->
                            val arguments = backStackEntry.arguments
                            val title = arguments?.getString("title")?.let { URLDecoder.decode(it, "UTF-8") } ?: ""
                            val description = arguments?.getString("description")?.let { URLDecoder.decode(it, "UTF-8") } ?: ""
                            NoteEdit(navController, Note(title, description)) { newTitle, newDescription ->
                                // Update the note
                                val updatedNote = notes.find { it.title == title && it.description == description }
                                updatedNote?.title = newTitle
                                updatedNote?.description = newDescription
                            }
                        }
                    }
                }
            }
        }
    }
}