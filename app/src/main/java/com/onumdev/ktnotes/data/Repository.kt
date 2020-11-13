package com.onumdev.ktnotes.data

import androidx.lifecycle.LiveData
import com.onumdev.ktnotes.databases.NotesDBTable
import com.onumdev.ktnotes.models.Note

object Repository: NotesRepository {
    override fun insertNote(note: Note) {
        NotesDBTable.notesTable?.insertNote(note)
    }

    override fun updateNoteTitle(title: String, body: String) {
        NotesDBTable.notesTable?.updateNoteTitle(title, body)
    }

    override fun updateNoteBody(title: String, body: String) {
        NotesDBTable.notesTable?.updateNoteBody(title, body)
    }

    override fun updateNote(title: String, body: String, whereClause: String) {
        NotesDBTable.notesTable?.updateNote(title, body, whereClause)
    }

    override fun getNoteLisFromDB(): LiveData<List<Note>> {
        return NotesDBTable.notesTable?.getNoteListFromDB() as LiveData<List<Note>>
    }

    override fun deleteNote(title: String, body: String) {
        NotesDBTable.notesTable?.deleteNote(title, body)
    }
}