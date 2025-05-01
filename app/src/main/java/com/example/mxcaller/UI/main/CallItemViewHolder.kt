package com.example.mxcaller.UI.main

import androidx.recyclerview.widget.RecyclerView
import com.example.mxcaller.Data.Model.CallDetails
import com.example.mxcaller.databinding.ItemCallLogBinding

class CallItemViewHolder (private val binding: ItemCallLogBinding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(callDetails: CallDetails) {
        binding.tvName.text = callDetails.name ?: "Unknown"
        binding.tvNumber.text = callDetails.number
        binding.tvDate.text = callDetails.date
        binding.tvType.text = callDetails.type
    }
}