package be.howest.jarnelosschaert.kipmetappelmoes.data.repos

import be.howest.jarnelosschaert.kipmetappelmoes.data.DummyData
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Review
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Tag

class DummyRestaurantRepo : IRestaurantRepo {

    override fun getAllRestaurants(): List<Restaurant> {
        return DummyData.restaurants
    }
    override fun getRestaurantsByFilter(tags: List<Tag>, search: String): List<Restaurant> {
        return DummyData.restaurants.filter { restaurant ->
          val restaurantTags = restaurant.eatingOptions + restaurant.childFriendliness
           val restaurantTagsMatch = restaurantTags.containsAll(tags)
           val restaurantNameMatch = restaurant.name.contains(search, true)
           restaurantTagsMatch && restaurantNameMatch
        }
    }

    override fun getRestaurant(id: Int): Restaurant {
        return DummyData.restaurants.first { it.id == id }
    }

    override fun getRestaurantsById(ids: List<Int>): List<Restaurant> {
        return DummyData.restaurants.filter { ids.contains(it.id) }
    }

    override fun addReview(review: Review, restaurant: Restaurant) {
                    DummyData.restaurants = DummyData.restaurants.map {
                        if (it.id == restaurant.id) {
                            it.copy(reviews = it.reviews + review)
                        } else {
                it
            }
        }
    }




}