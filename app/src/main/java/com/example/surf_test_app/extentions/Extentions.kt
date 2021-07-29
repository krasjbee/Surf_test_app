package com.example.surf_test_app.extentions

import java.text.SimpleDateFormat
import java.util.*


fun String.toDate(): String {
    try {
        val year = this.substring(0, 4).toIntOrNull() ?: 0
        val month = this.substring(5, 7).toIntOrNull() ?: 0
        val day = this.substring(8, 10).toIntOrNull() ?: 0
        val calendar = GregorianCalendar.getInstance()
        calendar.set(year, month, day)
        calendar.isLenient = false
        val format = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
        return try {
            format.format(calendar.time)
        } catch (e: Exception) {
            "Unable to get date"
        }
    } catch (e: Exception) {

    }
    return "Unable to get date"
}
