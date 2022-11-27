package com.example.letsrecyclethissheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuItemAdapter(private val items: List<Pair<String, String>>) :
    RecyclerView.Adapter<MenuItemViewHolder>() {
    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.menu_item, parent, false)
        return MenuItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, listener, position)
    }

    override fun getItemCount(): Int = items.size

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}

interface OnItemClickListener {
    fun onItemClick(title: TextView, position: Int)
    fun onButtonClick(button: Button, position: Int)
}