package com.neonfunapps.weathercast.data.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDate(isoDate: String): String {
    val dateTime = LocalDateTime.parse(isoDate, DateTimeFormatter.ISO_DATE_TIME)
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)

    return dateTime.format(formatter)
}

fun formatTemperature(temperature: Double): String {
    return "$temperature" + "\u2103"
}

fun formatDataExpressedAsPercentages(data: Double): String {
    return "$data%"
}

fun formatWindSpeed(windSpeed: Double): String {
    return "$windSpeed km/h"
}
