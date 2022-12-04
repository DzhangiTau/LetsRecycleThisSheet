package com.example.letsrecyclethissheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.letsrecyclethissheet.databinding.FragmentContactsBinding
import com.example.letsrecyclethissheet.databinding.FragmentMenuBinding
import com.example.letsrecyclethissheet.model.ContactModel

class ContactsFragment : Fragment() {
    private lateinit var viewModel: ContactsViewModel
    private lateinit var binding: FragmentContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false)
        viewModel = ViewModelProvider(this)[ContactsViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            addContactFab.setOnClickListener {
                val action = ContactsFragmentDirections.actionContactsFragmentToAddContactFragment()
                findNavController().navigate(action)
            }
        }

        val adapter = ContactAdapter(
            listOf(
                ContactModel(1, "John", "234234"),
                ContactModel(2, "John", "234234"),
                ContactModel(3, "John", "234234"),
                ContactModel(4, "John", "234234"),
                ContactModel(5, "John", "234234"),
                ContactModel(6, "John", "234234"),
            )
        )
        adapter.setOnClickListener(object : OnContactItemClickListener {
            override fun onContactClick(itemView: TextView, position: Int) {
                Toast.makeText(requireContext(), "${itemView.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onEditClick(itemView: ImageView, position: Int) {
                Toast.makeText(requireContext(), "Edit", Toast.LENGTH_SHORT).show()

            }

            override fun onDeleteClick(itemView: ImageView, position: Int) {
                Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show()
            }

        })
        binding.contactsRv.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        super.onViewCreated(view, savedInstanceState)
    }
}