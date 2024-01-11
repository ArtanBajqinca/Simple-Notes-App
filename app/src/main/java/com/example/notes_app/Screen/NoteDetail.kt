package com.example.notes_app.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.shadow
import androidx.navigation.NavController

@Composable
fun NoteDetail(navController: NavController, title: String, description: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            // Nested Box for the note details
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .shadow(4.dp, RoundedCornerShape(8.dp))
                    .background(
                        color = Color(0xFFF1F58F),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = description,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    )
                }
            }
            Spacer(modifier = Modifier
                .height(16.dp)
            )
            Button(onClick = {
                navController.navigateUp()
            },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Back")
            }
        }
    }
}