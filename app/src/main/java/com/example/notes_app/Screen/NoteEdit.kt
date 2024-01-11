package com.example.notes_app.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes_app.Screen.validateNote

@Composable
fun NoteEdit(navController: NavController, note: Note, onNoteUpdated: (title: String, description: String) -> Unit) {
    var titleState by remember { mutableStateOf(TextFieldValue(note.title)) }
    var descriptionState by remember { mutableStateOf(TextFieldValue(note.description)) }

    val validationResult = validateNote(titleState.text, descriptionState.text)
    val isValid = validationResult.isValid
    val errors = validationResult.errors

    Column {
        TextField(
            value = titleState,
            onValueChange = { titleState = it },
            label = { Text("Title") },
            modifier = Modifier.padding(16.dp),
            isError = errors.any { it.contains("title") } // Check if any error messages are related to the title
        )

        TextField(
            value = descriptionState,
            onValueChange = { descriptionState = it },
            label = { Text("Description") },
            modifier = Modifier.padding(16.dp),
            isError = errors.any { it.contains("text") } // Check if any error messages are related to the description
        )

        // Displaying the error messages
        errors.forEach { error ->
            Text(text = error, color = Color.Red, fontSize = 14.sp, modifier = Modifier.padding(start = 16.dp))
            Spacer(modifier = Modifier.height(4.dp))
        }

        Button(
            onClick = {
                if (isValid) {
                    onNoteUpdated(titleState.text, descriptionState.text)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.padding(16.dp),
            enabled = isValid  // Disable button if note is not valid
        ) {
            Text("Update")
        }
    }
}
