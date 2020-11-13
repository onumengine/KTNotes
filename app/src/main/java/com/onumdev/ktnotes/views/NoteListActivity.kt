package com.onumdev.ktnotes.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.onumdev.ktnotes.R
import com.onumdev.ktnotes.controllers.NotesRecyclerAdapter
import com.onumdev.ktnotes.databases.NotesDBObject
import com.onumdev.ktnotes.models.Note
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity(), NotesRecyclerAdapter.Listener {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: NotesRecyclerAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var listOfNotes: MutableList<Note>
    lateinit var coordinatorLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        coordinatorLayout = coordinator

        NotesDBObject.createTable(this)

        val fab = fab.apply { setOnClickListener { startNoteActivity() } }
    }

    override fun onStart() {
        super.onStart()

        listOfNotes = NotesDBObject.notesTable?.getNoteListFromDB()!!.toSet().toMutableList()

        recyclerView = findViewById(R.id.note_list_recyclerview)
        recyclerAdapter = NotesRecyclerAdapter(listOfNotes)
        layoutManager = LinearLayoutManager(this)

        recyclerAdapter.listener = this

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.notelist_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings_item -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDeleteButtonClick(noteTitle: String, noteBody: String) {
        NotesDBObject.notesTable?.deleteNote(noteTitle, noteBody)
        onStart()
        showSnackbar()
    }

    override fun onClickNote(title: String, body: String) {
        val noteActivityIntent = Intent(this, NoteActivity::class.java).apply {
            putExtra("title", title)
            putExtra("body", body)
        }
        startActivity(noteActivityIntent)
    }

    private fun startNoteActivity() {
        startActivity(Intent(this, NoteActivity::class.java))
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(coordinatorLayout, "Note deleted", Snackbar.LENGTH_SHORT)
        snackbar.show()
    }
}