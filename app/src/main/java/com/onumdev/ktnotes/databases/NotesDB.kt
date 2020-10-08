package com.onumdev.ktnotes.databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.onumdev.ktnotes.databases.NotesDBSchema.DATABASE_NAME
import com.onumdev.ktnotes.databases.NotesDBSchema.DATABASE_VERSION
import com.onumdev.ktnotes.databases.NotesDBSchema.NotesTable.Columns.KEY_NOTE_BODY
import com.onumdev.ktnotes.databases.NotesDBSchema.NotesTable.Columns.KEY_NOTE_ID
import com.onumdev.ktnotes.databases.NotesDBSchema.NotesTable.Columns.KEY_NOTE_TITLE
import com.onumdev.ktnotes.databases.NotesDBSchema.NotesTable.TABLE_NAME
import com.onumdev.ktnotes.models.Note

class NotesDB(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        val createCommand =
            "CREATE TABLE $TABLE_NAME ($KEY_NOTE_ID INTEGER PRIMARY KEY AUTOINCREMENT, \n$KEY_NOTE_TITLE TEXT, \n$KEY_NOTE_BODY TEXT)"
        db?.execSQL(createCommand)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropCommand = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropCommand)
    }

    fun insertNote(note: Note) {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(KEY_NOTE_TITLE, note.title)
            put(KEY_NOTE_BODY, note.body)
        }
        try {
            db.insert(TABLE_NAME, null, contentValues)
        } catch (e: SQLiteException) {
            e.message?.let { Log.e("NOTESDATABASE", it) }
        }
        db.close()
    }

    fun updateNoteTitle(title: String, body: String) {
        val db = writableDatabase
        val updatedTitle = ContentValues().apply {
            put(KEY_NOTE_TITLE, title)
        }
        db.update(
            TABLE_NAME,
            updatedTitle,
            "$KEY_NOTE_BODY = ?",
            arrayOf(body)
        )
        db.close()
    }

    fun updateNoteText(title: String, body: String) {
        val db = writableDatabase
        val updatedText = ContentValues().apply {
            put(KEY_NOTE_BODY, body)
        }
        db.update(
            TABLE_NAME,
            updatedText,
            "$KEY_NOTE_TITLE = ?",
            arrayOf(title)
        )
        db.close()
    }

    fun updateNote(title: String, body: String, whereClause: String) {
        val db = writableDatabase
        val updatedDetails = ContentValues().apply {
            put(KEY_NOTE_TITLE, title)
            put(KEY_NOTE_BODY, body)
        }
        db.update(
            TABLE_NAME,
            updatedDetails,
            "$KEY_NOTE_TITLE = ?",
            arrayOf(whereClause)
        )
        db.close()
    }

    fun getNoteListFromDB(): List<Note> {
        val db = readableDatabase
        val noteList = mutableListOf<Note>()
        val selectionQuery = "SELECT*FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectionQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val note = Note(cursor.getString(1), cursor.getString(2))
                noteList.add(note)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return noteList
    }

    fun deleteNote(title: String, body: String) {
        val db = writableDatabase
        db.delete(
            TABLE_NAME,
            "$KEY_NOTE_TITLE = ? AND $KEY_NOTE_BODY = ? ",
            arrayOf(title, body)
        )
        db.close()
    }
}