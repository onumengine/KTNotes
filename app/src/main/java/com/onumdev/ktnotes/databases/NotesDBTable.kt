package com.onumdev.ktnotes.databases

import android.content.Context

class NotesDBTable {

    companion object {

        var notesTable: NotesDB? = null
            get() = field

        fun createTable(context: Context) {

            if (notesTable == null) {
                notesTable = NotesDB(context)
            }

        }

    }

    private constructor()

}