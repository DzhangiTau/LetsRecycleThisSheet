package com.example.letsrecyclethissheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.letsrecyclethissheet.model.ContactModel

class ContactAdapter(private var items: MutableList<ContactModel> = mutableListOf()) :
    RecyclerView.Adapter<ContactItemViewHolder>() {

    private lateinit var listener: OnContactItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.contact_item, parent, false)
        return ContactItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, listener, position)
    }

    override fun getItemCount(): Int = items.size

    fun setOnClickListener(listener: OnContactItemClickListener) {
        this.listener = listener
    }

    fun updateContacts(contacts: MutableList<ContactModel>) {
        this.items.clear()
        this.items.addAll(contacts)
        notifyDataSetChanged()
    }
}

interface OnContactItemClickListener {
    fun onContactClick(itemView: TextView, position: Int)
    fun onEditClick(contact: ContactModel, position: Int)
    fun onDeleteClick(contact: ContactModel, position: Int)
}