package be.howest.jarnelosschaert.kipmetappelmoes.data.models;

data class Restaurant(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val postalCode: String,
    val latitude: Double,
    val longitude: Double,
    val phone: String,
    val email: String,
    val website: String,
    val openingHours: Map<String, String>,
    val description: String,
    val images: List<String>,
    val childFriendliness: Set<ChildFriendliness>,
    val eatingOptions: Set<EatingOption>,
    val reviews: List<Review>
)
