package com.example.letsrecyclethissheet

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MenuItemViewHolder(private val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    private val title: TextView = itemView.findViewById(R.id.title_tv)
    private val button: Button = itemView.findViewById(R.id.btn)

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(itemData: Pair<String, String>) {
        title.text = itemData.first
        button.text = itemData.second
    }

    override fun onClick(v: View?) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            Toast.makeText(context, "Item ${button.text} was clicked", Toast.LENGTH_SHORT).show()
        }
    }
}