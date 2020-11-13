package com.onumdev.ktnotes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onumdev.ktnotes.data.NotesRepository
import com.onumdev.ktnotes.data.Repository
import com.onumdev.ktnotes.models.Note
import kotlin.concurrent.timerTask

class NoteListViewModel: ViewModel() {
    private val listOfNotes: MutableLiveData<List<Note>?>? = retrieveNotes()

    fun getListOfNotes(): LiveData<List<Note>?>? {
        return listOfNotes
    }

    private fun retrieveNotes(): MutableLiveData<List<Note>?>? {
        return Repository.getNoteListFromDB() as MutableLiveData<List<Note>?>?
    }

    fun insertNote(note: Note) {
        Repository.insertNote(note)
    }

    fun updateNoteTitle(title: String, body: String) {
        Repository.updateNoteTitle(title, body)
    }

    fun updateNoteBody(title: String, body: String) {
        Repository.updateNoteBody(title, body)
    }

    fun updateNote(title: String, body: String, whereClause: String) {
        Repository.updateNote(title, body, whereClause)
    }

    fun deleteNote(title: String, body: String) {
        Repository.deleteNote(title, body)
    }
}