package com.onumdev.ktnotes.data

import androidx.lifecycle.LiveData
import com.onumdev.ktnotes.models.Note

interface NotesRepository {
    fun insertNote(note: Note)
    fun updateNoteTitle(title: String, body: String)
    fun updateNoteBody(title: String, body: String)
    fun updateNote(title: String, body: String, whereClause: String)
    fun getNoteListFromDB(): LiveData<List<Note>>
    fun deleteNote(title: String, body: String)
}