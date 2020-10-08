package com.onumdev.ktnotes.databases

object NotesDBSchema {
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "notesdb"
    object NotesTable {
        const val TABLE_NAME = "notestable"
        object Columns {
            const val KEY_NOTE_ID = "_id"
            const val KEY_NOTE_TITLE = "title"
            const val KEY_NOTE_BODY = "body"
        }
    }
}