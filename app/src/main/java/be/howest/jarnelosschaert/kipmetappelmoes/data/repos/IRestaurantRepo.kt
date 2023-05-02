package be.howest.jarnelosschaert.kipmetappelmoes.data.repos

import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Review
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Tag

interface IRestaurantRepo {
    fun getAllRestaurants(): List<Restaurant>
    fun getRestaurantsByFilter(tags: List<Tag>, search: String): List<Restaurant>
    fun getRestaurantsById(ids: List<Int>): List<Restaurant>
    fun getRestaurant(id: Int): Restaurant

    fun addReview(review: Review, restaurant: Restaurant)
}