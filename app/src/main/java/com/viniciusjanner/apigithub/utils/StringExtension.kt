package com.viniciusjanner.apigithub.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.toBrazilianDate(): String {
    return try {
        val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        isoFormatter.timeZone = TimeZone.getTimeZone("UTC")

        val brazilFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale("pt", "BR"))
        val date: Date = isoFormatter.parse(this) ?: return this
        brazilFormatter.format(date)

    } catch (e: Exception) {
        this
    }
}
