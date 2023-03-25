package be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers

import be.howest.jarnelosschaert.kipmetappelmoes.data.Restaurant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.roundToInt

fun calculateAverageRating(restaurant: Restaurant): Int {
    var total = 0
    for (review in restaurant.reviews) {
        total += review.rating
    }
    return (total.toFloat() / restaurant.reviews.size).roundToInt()
}

fun getTodayOpeningHours(restaurant: Restaurant): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("EEEE")
    val currentDay = currentDateTime.format(formatter)
    return restaurant.openingHours[currentDay] ?: "Closed"
}

fun getEatingOptions(Restaurant: Restaurant): String {
    var eatingOptions = "~"
    for (eatingOption in Restaurant.eatingOptions) {
        val eatingOptionString = eatingOption.name.lowercase(Locale.getDefault())
        eatingOptions += "$eatingOptionString~"
    }
    return eatingOptions
}

fun timeAgo(date: LocalDate): String {
    val now = LocalDate.now()
    val years = ChronoUnit.YEARS.between(date, now)
    val months = ChronoUnit.MONTHS.between(date.withDayOfMonth(1), now.withDayOfMonth(1))
    val days = ChronoUnit.DAYS.between(date, now)

    return when {
        years > 0 -> "($years ${if (years == 1L) "year" else "years"} ago)"
        months > 0 -> "($months ${if (months == 1L) "month" else "months"} ago)"
        else -> "($days ${if (days == 1L) "day" else "days"} ago)"
    }
}