package com.example.notes_app.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notes_app.Screen.validateNote

@Composable
fun NoteCreate(navController: NavController, onNoteCreated: (title: String, description: String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val validationResult = validateNote(title, description)
    val isValid = validationResult.isValid
    val errors = validationResult.errors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create note")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            isError = errors.any { it.contains("title") } // Check if any error messages are related to the title
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            isError = errors.any { it.contains("text") } // Check if any error messages are related to the description
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Displaying the error messages
        errors.forEach { error ->
            Text(text = error, color = Color.Red, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Back")
            }

            Button(onClick = {
                if (isValid) {
                    onNoteCreated(title, description)
                    navController.navigateUp()
                }
            }, enabled = isValid) { // Disable button if note is not valid
                Text("Save")
            }
        }
    }
}
