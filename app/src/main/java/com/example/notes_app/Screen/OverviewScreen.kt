package com.example.notes_app.Screen

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.navigation.NavController
import com.example.notes_app.R
import java.net.URLEncoder
import kotlin.reflect.KFunction1

val CustomBlue = Color(0xFF002D62)

data class Note(var title: String, var description: String)

@Composable
fun OverviewScreen(
    navController: NavController,
    notes: List<Note>,
    deleteNote: (Note) -> Unit,
    editNote: (Note) -> Unit
) {

    Column(
        modifier = Modifier.
        fillMaxSize()
    ) {
        TopAppBar()

        // Display the list of notes
        LazyColumn (
            modifier = Modifier
            .padding(top = 12.dp)
        ){
            items(notes) { note ->
                NoteItem(note = note, navController = navController, deleteNote = deleteNote, editNote = editNote)
            }
        }
        CreateNoteButton(navController)
    }
}

@Composable
fun TopAppBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CustomBlue)
            .height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "NOTES",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.width(14.dp))
            Icon(
                painter = painterResource(id = R.drawable.noteslogo),
                contentDescription = "Notes Logo",
                modifier = Modifier.size(40.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    navController: NavController,
    deleteNote: (Note) -> Unit,
    editNote: (Note) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .background(
                color = Color(0xFFF1F58F),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    navController.navigate("${Screen.Detail.route}/${URLEncoder.encode(note.title, "UTF-8")}/${URLEncoder.encode(note.description, "UTF-8")}")
                }
        ) {
            Text(
                text = note.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
        }

        Row { // Containing both edit and delete icons
            IconButton(onClick = {
                // Navigate to the edit screen for the chosen note
                navController.navigate("${Screen.NoteEdit.route}/${URLEncoder.encode(note.title,
                    "UTF-8")}/${URLEncoder.encode(note.description, "UTF-8")}")
            }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Note")
            }

            IconButton(onClick = { deleteNote(note) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Note")
            }
        }
    }
}

@Composable
fun CreateNoteButton(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = { navController.navigate(Screen.NoteCreate.route) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(80.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Create a note",
                modifier = Modifier.size(80.dp),
                tint = CustomBlue
            )
        }
    }
}