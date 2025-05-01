package com.example.mxcaller.UI.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mxcaller.Data.Model.CallDetails
import com.example.mxcaller.databinding.ItemCallLogBinding

class MainAdapter() : ListAdapter<CallDetails, CallItemViewHolder>(CallDetailsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallItemViewHolder {
        val binding = ItemCallLogBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CallItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CallItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}