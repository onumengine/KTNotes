package com.onumdev.ktnotes.views

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.onumdev.ktnotes.R
import com.onumdev.ktnotes.databases.NotesDBTable
import com.onumdev.ktnotes.models.Note
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {

    private lateinit var noteTitleInput: EditText
    private lateinit var noteBodyInput: EditText
    private lateinit var rootLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val toolbar = toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rootLayout = root_layout
        noteTitleInput = note_title_textview
        noteBodyInput = note_body_textview

        populateTextFields()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.noteactivity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_action -> deleteNote()
            R.id.discard_action -> discardNote()
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        tryToSaveNote()
    }

    private fun tryToSaveNote() {
        val noteTitle: String? = noteTitleInput.text.toString()
        val noteBody: String? = noteBodyInput.text.toString()

        if (noteTitle!!.isNotEmpty() && noteBody!!.isNotEmpty()) {

            if (intent.extras != null) {

                val titleExtra = intent.getStringExtra("title")
                val bodyExtra = intent.getStringExtra("text")

                if (noteTitle != titleExtra && noteBody != bodyExtra) {

                    if (titleExtra != null) {
                        NotesDBTable.notesTable?.updateNote(noteTitle, noteBody, titleExtra)
                    }

                } else if (noteTitle != titleExtra) {

                    updateNoteTitle(noteTitle, noteBody)

                } else if (noteBody != bodyExtra) {

                    updateNoteBody(noteTitle, noteBody)
                }
            } else {
                saveContentsOfTextFieldsAsNote(noteTitle, noteBody)
            }

        }
    }



    private fun updateNoteTitle(noteTitle: String, noteBody: String) {
        NotesDBTable.notesTable?.updateNoteTitle(noteTitle, noteBody)
    }

    private fun updateNoteBody(noteTitle: String, noteBody: String) {
        NotesDBTable.notesTable?.updateNoteBody(noteTitle, noteBody)
    }

    private fun saveContentsOfTextFieldsAsNote(title: String, body: String) {
        val note = Note(title, body)
        NotesDBTable.notesTable?.insertNote(note)
    }

    private fun populateTextFields() {
        (intent.getStringExtra("title"))?.let {
            noteTitleInput.setText(it)
        }
        (intent.getStringExtra("body"))?.let {
            noteBodyInput.setText(it)
        }
    }

    private fun deleteNote() {
        val noteTitle = noteTitleInput.text.toString()
        val noteBody = noteBodyInput.text.toString()

        NotesDBTable.notesTable?.deleteNote(noteTitle, noteBody)
        clearTextFields()
        showSnackBar("Note deleted")
        returnToNotesListActivity()
    }

    private fun discardNote() {
        clearTextFields()
        showSnackBar("Note deleted")
        returnToNotesListActivity()
    }

    private fun clearTextFields() {
        noteTitleInput.text = null
        noteBodyInput.text = null
    }

    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

    private fun returnToNotesListActivity() {
        Handler().postDelayed({ goBackToNoteListActivity() }, 2000)
    }

    private fun goBackToNoteListActivity() {
        super.onBackPressed()
    }
}