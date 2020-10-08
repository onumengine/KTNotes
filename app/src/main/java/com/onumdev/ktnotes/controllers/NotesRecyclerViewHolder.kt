package com.onumdev.ktnotes.controllers

import android.animation.AnimatorInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.onumdev.ktnotes.R

class NotesRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var noteTitleTextView: TextView = itemView.findViewById(R.id.viewholder_note_title_textView)
    var noteBodyTextView: TextView = itemView.findViewById(R.id.viewholder_note_text_textView)
    var deleteButton: ImageButton = itemView.findViewById(R.id.viewholder_delete_button)
    var cardView: CardView = itemView.findViewById(R.id.viewholder_cardview)

    init {
        itemView.setOnLongClickListener {
            expandViewHorizontally(it)
            expandViewVertically(it)
            changeVisibility(deleteButton)
            true
        }
    }

    fun changeVisibility(view: View) {
        if (view.visibility != View.VISIBLE) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    fun expandViewHorizontally(view: View) {
        val animator = AnimatorInflater.loadAnimator(view.context, R.animator.scalex)
        animator.setTarget(view)
        animator.start()
    }

    fun expandViewVertically(view: View) {
        val animator = AnimatorInflater.loadAnimator(view.context, R.animator.scaley)
        animator.setTarget(view)
        animator.start()
    }

}