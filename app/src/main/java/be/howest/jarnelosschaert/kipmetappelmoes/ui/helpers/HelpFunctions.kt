package be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import be.howest.jarnelosschaert.kipmetappelmoes.R
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

@Composable
fun getTodayOpeningHours(restaurant: Restaurant): String {
    val currentDate = LocalDate.now()
    val dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale("nl", "NL"))
    val day = currentDate.format(dayFormatter)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    return restaurant.openingHours[day] ?: stringResource(R.string.closed)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun timeAgo(date: LocalDate): String {
    val now = LocalDate.now()
    val years = ChronoUnit.YEARS.between(date, now)
    val months = ChronoUnit.MONTHS.between(date.withDayOfMonth(1), now.withDayOfMonth(1))
    val days = ChronoUnit.DAYS.between(date, now)

    val amount: Int
    val unit: String
    when {
        years > 0 -> {
            amount = years.toInt()
            unit = pluralStringResource(id = R.plurals.days, amount)
        }
        months > 0 -> {
            amount = months.toInt()
            unit = pluralStringResource(id = R.plurals.months, amount)
        }
        days > 0 -> {
            amount = days.toInt()
            unit = pluralStringResource(id = R.plurals.years, amount)
        } else -> {
            return stringResource(id = R.string.today)
        }
    }
    return stringResource(id = R.string.ago, amount.toString(), unit)
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

