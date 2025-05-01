package com.example.mxcaller.UI.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mxcaller.Data.Model.Contact
import com.example.mxcaller.databinding.ItemContactBinding

class ContactsAdapter: RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    private val contactList = mutableListOf<Contact>()

    inner class ContactViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.tvContactName.text = contact.name
            binding.tvContactNumber.text = contact.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size

    fun submitList(list: List<Contact>) {
        contactList.clear()
        contactList.addAll(list)
        notifyDataSetChanged()
    }

}