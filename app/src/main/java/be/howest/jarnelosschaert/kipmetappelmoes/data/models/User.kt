package be.howest.jarnelosschaert.kipmetappelmoes.data.models

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val favorites: List<Restaurant>
)
