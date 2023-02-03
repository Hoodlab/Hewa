package com.example.hewa.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

object Utils {
    val date = Calendar.getInstance().time

    fun getFormattedDate(): String {
        val format = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.ENGLISH)
        return format.format(date)
    }

    fun getFormattedTime(): String {
        val format = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        return format.format(date)
    }


}