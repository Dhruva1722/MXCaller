package com.example.mxcaller.UI.main

import android.content.Context
import android.provider.CallLog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mxcaller.Data.Model.CallDetails
import com.example.mxcaller.Data.Model.CallLogRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel : ViewModel()  {
    private val _callLogs = MutableLiveData<List<CallDetails>>()
    val callLogs: LiveData<List<CallDetails>> get() = _callLogs

    private val repository = CallLogRepository()

    fun fetchCallLogs(context: Context) {
        val logs = repository.getCallLogs(context)
        _callLogs.postValue(logs)
    }



}