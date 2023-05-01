package be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.EatingOption
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.SortType
import com.google.accompanist.flowlayout.FlowRow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
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
    val currentDate = LocalDate.now()
    val dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale("nl", "NL"))
    val day = currentDate.format(dayFormatter)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    return restaurant.openingHours[day] ?: "Gesloten"
}

fun timeAgo(date: LocalDate): String {
    val now = LocalDate.now()
    val years = ChronoUnit.YEARS.between(date, now)
    val months = ChronoUnit.MONTHS.between(date.withDayOfMonth(1), now.withDayOfMonth(1))
    val days = ChronoUnit.DAYS.between(date, now)

    return when {
        years > 0 -> "($years ${if (years == 1L) "jaar" else "jaren"} geleden)"
        months > 0 -> "($months ${if (months == 1L) "maand" else "maanden"} geleden)"
        days > 0 -> "($days ${if (days == 1L) "dag" else "dagen"} geleden)"
        else -> "(vandaag)"
    }
}

fun getSortedRestaurants(restaurants: List<Restaurant>, sortType: SortType): List<Restaurant> {
    return when (sortType) {
        SortType.RATING -> restaurants.sortedByDescending { calculateAverageRating(it) }
        SortType.NAME -> restaurants.sortedBy { it.name }
        else -> { restaurants }
    }
}

fun getSortedEatingOptions(eatingOptions: Set<EatingOption>): List<EatingOption> {
    return eatingOptions.sortedWith(compareBy { EatingOption.valueOf(it.toString()) })
}

