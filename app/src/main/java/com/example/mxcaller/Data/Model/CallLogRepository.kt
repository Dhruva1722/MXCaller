package com.example.mxcaller.Data.Model

import android.content.Context
import android.provider.CallLog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CallLogRepository {

    fun getCallLogs(context: Context): List<CallDetails> {
        val list = mutableListOf<CallDetails>()
        val cursor = context.contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            null, null, null,
            CallLog.Calls.DATE + " DESC"
        )

        cursor?.use {
            val nameIndex = it.getColumnIndex(CallLog.Calls.CACHED_NAME)
            val numberIndex = it.getColumnIndex(CallLog.Calls.NUMBER)
            val dateIndex = it.getColumnIndex(CallLog.Calls.DATE)
            val durationIndex = it.getColumnIndex(CallLog.Calls.DURATION)
            val typeIndex = it.getColumnIndex(CallLog.Calls.TYPE)

            while (it.moveToNext()) {
                val name = it.getString(nameIndex)
                val number = it.getString(numberIndex)
                val dateMillis = it.getLong(dateIndex)
                val duration = it.getString(durationIndex)
                val typeCode = it.getInt(typeIndex)

                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val date = sdf.format(Date(dateMillis))

                val type = when (typeCode) {
                    CallLog.Calls.INCOMING_TYPE -> "Incoming"
                    CallLog.Calls.OUTGOING_TYPE -> "Outgoing"
                    CallLog.Calls.MISSED_TYPE -> "Missed"
                    else -> "Other"
                }

                list.add(CallDetails(name, number, date, duration, type))
            }
        }

        return list
    }
}