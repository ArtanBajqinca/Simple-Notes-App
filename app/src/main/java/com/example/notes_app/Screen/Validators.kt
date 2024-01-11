package com.example.notes_app.Screen

data class ValidationResult(val isValid: Boolean, val errors: List<String>)

fun validateNote(title: String, description: String): ValidationResult {
    val errors = mutableListOf<String>()

    if (title.length < 3) errors.add("The title must contain at least 3 characters.")
    if (title.length > 50) errors.add("The title must contain at most 50 characters.")
    if (description.length > 120) errors.add("The text must contain at most 120 characters.")

    return ValidationResult(errors.isEmpty(), errors)
}