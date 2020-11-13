package com.onumdev.ktnotes.data

import androidx.lifecycle.LiveData
import com.onumdev.ktnotes.databases.NotesDBObject
import com.onumdev.ktnotes.models.Note

object Repository: NotesRepository {
    override fun insertNote(note: Note) {
        NotesDBObject.notesDB?.insertNote(note)
    }

    override fun updateNoteTitle(title: String, body: String) {
        NotesDBObject.notesDB?.updateNoteTitle(title, body)
    }

    override fun updateNoteBody(title: String, body: String) {
        NotesDBObject.notesDB?.updateNoteBody(title, body)
    }

    override fun updateNote(title: String, body: String, whereClause: String) {
        NotesDBObject.notesDB?.updateNote(title, body, whereClause)
    }

    override fun getNoteListFromDB(): LiveData<List<Note>> {
        return NotesDBObject.notesDB?.getNoteListFromDB() as LiveData<List<Note>>
    }

    override fun deleteNote(title: String, body: String) {
        NotesDBObject.notesDB?.deleteNote(title, body)
    }
}