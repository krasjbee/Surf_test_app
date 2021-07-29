package com.example.surf_test_app.extentions

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


fun String.toDate(): String {
    val year = this.substring(0, 4).toInt()
    val month = this.substring(5, 7).toInt()
    val day = this.substring(8, 10).toInt()
    Log.d("date", "toDate: $year $month $day ")
    val calendar = GregorianCalendar.getInstance()
    calendar.set(year, month, day)
    calendar.isLenient = false
    val format = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
    return try {
        format.format(calendar.time)
    } catch (e: Exception) {
        "Error to get date"
    }
}
