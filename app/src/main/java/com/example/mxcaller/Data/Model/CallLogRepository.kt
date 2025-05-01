package com.example.mxcaller.Data.Model

import android.content.Context
import android.net.Uri
import android.provider.CallLog
import android.provider.ContactsContract
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CallLogRepository {

    fun getCallLogs(context: Context): List<CallDetails> {
        val list = mutableListOf<CallDetails>()
        val seenNumbers = mutableSetOf<String>()

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
                val number = it.getString(numberIndex) ?: continue
                if (seenNumbers.contains(number)) continue
                seenNumbers.add(number)

                val name = it.getString(nameIndex)
                val dateMillis = it.getLong(dateIndex)
                val duration = it.getString(durationIndex)
                val typeCode = it.getInt(typeIndex)
                val dateFormatted = formatDate(dateMillis)
                val type = when (typeCode) {
                    CallLog.Calls.INCOMING_TYPE -> "Incoming"
                    CallLog.Calls.OUTGOING_TYPE -> "Outgoing"
                    CallLog.Calls.MISSED_TYPE -> "Missed"
                    else -> "Other"
                }
                val photoUri = getContactPhotoUri(context, number)

                list.add(CallDetails(name, number, dateFormatted, duration, type, photoUri))
            }
        }

        return list
    }

    private fun formatDate(timeMillis: Long): String {
        val cal = Calendar.getInstance()
        val now = Calendar.getInstance()
        cal.timeInMillis = timeMillis

        return when {
            now.get(Calendar.DATE) == cal.get(Calendar.DATE) -> "Today"
            now.get(Calendar.DATE) - cal.get(Calendar.DATE) == 1 -> "Yesterday"
            else -> SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(timeMillis))
        }
    }

    private fun getContactPhotoUri(context: Context, number: String): String? {
        val uri = Uri.withAppendedPath(
            ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
            Uri.encode(number)
        )
        val cursor = context.contentResolver.query(
            uri, arrayOf(ContactsContract.PhoneLookup.PHOTO_URI),
            null, null, null
        )
        cursor?.use {
            if (it.moveToFirst()) {
                return it.getString(0)
            }
        }
        return null
    }
}