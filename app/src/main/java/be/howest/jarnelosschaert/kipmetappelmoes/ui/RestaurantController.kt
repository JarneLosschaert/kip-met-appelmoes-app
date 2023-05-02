package be.howest.jarnelosschaert.kipmetappelmoes.ui

import be.howest.jarnelosschaert.kipmetappelmoes.data.DummyData
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Review
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Tag
import be.howest.jarnelosschaert.kipmetappelmoes.data.repos.DummyRestaurantRepo
import be.howest.jarnelosschaert.kipmetappelmoes.data.repos.IRestaurantRepo
import be.howest.jarnelosschaert.kipmetappelmoes.uiState
import java.time.LocalDate

class RestaurantController() {

    private val restaurantRepo: IRestaurantRepo = DummyRestaurantRepo()

    fun getAllRestaurants(): List<Restaurant> {
        return restaurantRepo.getAllRestaurants()
    }

    fun getRestaurantsByFilter(tags: List<Tag>, search: String = ""): List<Restaurant> {
        return restaurantRepo.getRestaurantsByFilter(tags, search)
    }

    fun getRestaurantsById(ids: List<Int>): List<Restaurant> {
        return restaurantRepo.getRestaurantsById(ids)
    }

    fun getRestaurant(id: Int): Restaurant {
        return restaurantRepo.getRestaurant(id)
    }

    fun addReview(content: String, rating: Int, restaurant: Restaurant) {
        val user = uiState.currentUser
        val review = Review(0, user, content, rating, LocalDate.now())
        restaurantRepo.addReview(review, restaurant)
    }
}