package com.example.letsrecyclethissheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.letsrecyclethissheet.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MenuItemAdapter(Utils.generateMenuItems(100))
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(title: TextView, position: Int) {
                Toast.makeText(requireContext(), "${title.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onButtonClick(button: Button, position: Int) {
                Toast.makeText(requireContext(), "${button.text}", Toast.LENGTH_SHORT).show()
            }
        })
        binding.recyclerRv.apply {
            setHasFixedSize(true)
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())

        }
    }

    private fun initAdapter() {

    }
}