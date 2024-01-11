package com.example.notes_app.Screen

sealed class Screen(val route: String){
    object Home : Screen(route = "OverviewScreen")
    object Detail : Screen(route = "NoteDetail/{title}/{description}")

    object NoteCreate : Screen(route = "NoteCreate")
    object NoteEdit : Screen(route = "NoteEdit/{title}/{description}")

}
