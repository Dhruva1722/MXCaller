package com.example.mxcaller.UI.main

import androidx.recyclerview.widget.RecyclerView
import com.example.mxcaller.Data.Model.CallDetails
import com.example.mxcaller.R
import com.bumptech.glide.Glide
import com.example.mxcaller.databinding.ItemCallLogBinding

class CallItemViewHolder (private val binding: ItemCallLogBinding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(call: CallDetails) {
        binding.tvName.text = if (!call.name.isNullOrBlank()) call.name else "Unknown"
        binding.tvNumber.text = call.number
        binding.tvDate.text = call.date

        val icon = when (call.type) {
            "Incoming" -> R.drawable.callin
            "Outgoing" -> R.drawable.outgoingcall
            "Missed" -> R.drawable.missedcall
            else -> R.drawable.phone
        }
        binding.ivCallTypeIcon.setImageResource(icon)

        Glide.with(binding.root.context)
            .load(call.photoUri)
            .placeholder(R.drawable.user)
            .into(binding.ivPhoto)
    }
}