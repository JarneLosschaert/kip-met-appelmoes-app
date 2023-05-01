package be.howest.jarnelosschaert.kipmetappelmoes.data.models

import java.time.LocalDate

data class Review(
    val id: Int,
    val user: User,
    val content: String,
    val rating: Int,
    val date: LocalDate
)
