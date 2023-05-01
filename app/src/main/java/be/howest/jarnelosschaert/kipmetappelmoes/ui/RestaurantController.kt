package be.howest.jarnelosschaert.kipmetappelmoes.ui

import be.howest.jarnelosschaert.kipmetappelmoes.data.DummyData
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Review
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Tag
import be.howest.jarnelosschaert.kipmetappelmoes.data.repos.DummyRestaurantRepo
import be.howest.jarnelosschaert.kipmetappelmoes.data.repos.IRestaurantRepo
import java.time.LocalDate

class RestaurantController() {

    private val repo: IRestaurantRepo = getRepo()

    private fun getRepo(): IRestaurantRepo {
        return if (online()) {
            DummyRestaurantRepo()
        } else {
            DummyRestaurantRepo()
        }
    }

    private fun online() : Boolean {
        return false
    }

    fun getAllRestaurants(): List<Restaurant> {
        return repo.getAllRestaurants()
    }

    fun getRestaurants(tags: List<Tag>, search: String = ""): List<Restaurant> {
        return repo.getRestaurants(tags, search)
    }

    fun addReview(content: String, rating: Int, restaurant: Restaurant) {
        val user = DummyData.me
        val review = Review(0, user, content, rating, LocalDate.now())
        repo.addReview(review, restaurant)
    }

    fun addFavorite(restaurant: Restaurant) {
        repo.addFavorite(restaurant)
    }

    fun removeFavorite(restaurant: Restaurant) {
        repo.removeFavorite(restaurant)
    }

    fun getFavorites(): List<Restaurant> {
        return repo.getFavorites()
    }

    fun getRestaurant(id: Int): Restaurant {
        return repo.getRestaurant(id)
    }
}