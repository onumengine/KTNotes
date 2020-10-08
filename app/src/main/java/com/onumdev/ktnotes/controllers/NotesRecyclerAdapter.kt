package com.onumdev.ktnotes.controllers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onumdev.ktnotes.R
import com.onumdev.ktnotes.models.Note

class NotesRecyclerAdapter(val listOfNotes: MutableList<Note>): RecyclerView.Adapter<NotesRecyclerViewHolder>() {

    var listener: Listener? = null

    interface Listener {
        fun onDeleteButtonClick(noteTitle: String, noteBody: String)
        fun onClickNote(title: String, body: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.notes_viewholder,
            parent,
            false
        )
        return NotesRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesRecyclerViewHolder, position: Int) {
        holder.noteTitleTextView.text = listOfNotes.get(position).title
        holder.noteBodyTextView.text = listOfNotes.get(position).body
        holder.deleteButton.setOnClickListener {
            listener?.onDeleteButtonClick(
                    holder.noteTitleTextView.text.toString(),
                    holder.noteBodyTextView.text.toString()
                )
        }
        holder.itemView.setOnClickListener {
            if (holder.deleteButton.visibility != View.VISIBLE) {
                listener!!.onClickNote(
                    holder.noteTitleTextView.text.toString(),
                    holder.noteBodyTextView.text.toString()
                )
            } else {
                holder.deleteButton.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }
}