package com.example.letsrecyclethissheet

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuItemViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.title_tv)
    private val button: Button = itemView.findViewById(R.id.btn)

    fun bind(
        itemData: Pair<String, String>,
        listener: OnItemClickListener,
        position: Int
    ) {
        title.text = itemData.first
        button.text = itemData.second

        itemView.setOnClickListener {
            listener.onItemClick(title, position)
        }
        button.setOnClickListener {
            listener.onButtonClick(button, position)
        }
    }
}