package com.onumdev.ktnotes.data

import androidx.lifecycle.LiveData
import com.onumdev.ktnotes.databases.NotesDBObject
import com.onumdev.ktnotes.models.Note

object Repository: NotesRepository {
    override fun insertNote(note: Note) {
        NotesDBObject.notesTable?.insertNote(note)
    }

    override fun updateNoteTitle(title: String, body: String) {
        NotesDBObject.notesTable?.updateNoteTitle(title, body)
    }

    override fun updateNoteBody(title: String, body: String) {
        NotesDBObject.notesTable?.updateNoteBody(title, body)
    }

    override fun updateNote(title: String, body: String, whereClause: String) {
        NotesDBObject.notesTable?.updateNote(title, body, whereClause)
    }

    override fun getNoteListFromDB(): LiveData<List<Note>> {
        return NotesDBObject.notesTable?.getNoteListFromDB() as LiveData<List<Note>>
    }

    override fun deleteNote(title: String, body: String) {
        NotesDBObject.notesTable?.deleteNote(title, body)
    }
}