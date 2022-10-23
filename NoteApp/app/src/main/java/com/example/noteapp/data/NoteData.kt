package com.example.noteapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.noteapp.model.Note

class NotesDataSource {
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "1", description = "1"),
            Note(title = "2", description = "2"),
            Note(title = "3", description = "3"),
            Note(title = "4", description = "4"),
            Note(title = "5", description = "5")
        )
    }
}