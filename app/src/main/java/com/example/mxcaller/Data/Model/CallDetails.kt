package com.example.mxcaller.Data.Model

data class CallDetails (
    val name: String?,
    val number: String,
    val date: String,
    val duration: String,
    val type: String,
    val photoUri: String? = null
)