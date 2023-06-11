package be.howest.jarnelosschaert.kipmetappelmoes.data.models

import be.howest.jarnelosschaert.kipmetappelmoes.R

enum class EatingOption(override val value: Int, val image: Int) : Tag {
    DINNER(R.string.dinner, R.drawable.dinner),
    LUNCH(R.string.lunch, R.drawable.lunch),
    BREAKFAST(R.string.breakfast, R.drawable.breakfast),
    SNACKS(R.string.snacks , R.drawable.snacks),
    DRINKS(R.string.drinks , R.drawable.drinks),
    TAKEOUT(R.string.takeout , R.drawable.takeout)
}
