package com.onumdev.ktnotes.databases

import android.content.Context

object NotesDBObject {

    var notesDB: NotesDB? = null
        get() = field

    fun createTable(context: Context) {

        if (notesDB == null) {
            notesDB = NotesDB(context)
        }

    }

}