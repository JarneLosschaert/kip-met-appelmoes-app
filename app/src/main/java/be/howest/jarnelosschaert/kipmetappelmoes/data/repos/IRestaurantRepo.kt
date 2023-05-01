package be.howest.jarnelosschaert.kipmetappelmoes.data.repos

import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Review
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Tag

interface IRestaurantRepo {
    fun getRestaurants(tags: List<Tag>, search: String): List<Restaurant>
    fun addReview(review: Review, restaurant: Restaurant)
    fun getAllRestaurants(): List<Restaurant>
    fun addFavorite(restaurant: Restaurant)
    fun getFavorites(): List<Restaurant>
    fun removeFavorite(restaurant: Restaurant)
    fun getRestaurant(id: Int): Restaurant
}