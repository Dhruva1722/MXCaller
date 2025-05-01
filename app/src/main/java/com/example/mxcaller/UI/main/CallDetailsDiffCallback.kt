package com.example.mxcaller.UI.main

import androidx.recyclerview.widget.DiffUtil
import com.example.mxcaller.Data.Model.CallDetails

object CallDetailsDiffCallback : DiffUtil.ItemCallback<CallDetails>() {
    override fun areItemsTheSame(oldItem: CallDetails, newItem: CallDetails): Boolean {
        return oldItem.number == newItem.number && oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: CallDetails, newItem: CallDetails): Boolean {
        return oldItem == newItem
    }
}