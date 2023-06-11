package be.howest.jarnelosschaert.kipmetappelmoes

import be.howest.jarnelosschaert.kipmetappelmoes.data.DummyData
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.*
import be.howest.jarnelosschaert.kipmetappelmoes.data.repos.DummyRestaurantRepo
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class DummyRestaurantRepoTest {

    private lateinit var dummyRestaurantRepo: DummyRestaurantRepo

    @Before
    fun setup() {
        dummyRestaurantRepo = DummyRestaurantRepo()
    }

    @Test
    fun testGetAllRestaurants() {
        val restaurants = dummyRestaurantRepo.getAllRestaurants()
        assertEquals(DummyData.restaurants.size, restaurants.size)
    }

    @Test
    fun testGetRestaurantsByFilter() {
        val tags = listOf(ChildFriendliness.HIGHCHAIRS, ChildFriendliness.PARKING)
        val search = "Den Huzaar"
        val filteredRestaurants = dummyRestaurantRepo.getRestaurantsByFilter(tags, search)
        assertEquals(1, filteredRestaurants.size)
    }

    @Test
    fun testGetRestaurant() {
        val restaurantId = 1
        val restaurant = dummyRestaurantRepo.getRestaurant(restaurantId)
        assertEquals(restaurantId, restaurant.id)
    }

    @Test
    fun testGetRestaurantsById() {
        val restaurantIds = listOf(1, 2, 3)
        val restaurants = dummyRestaurantRepo.getRestaurantsById(restaurantIds)
        assertEquals(restaurantIds.size, restaurants.size)
    }

    @Test
    fun testAddReview() {
        val review = Review(0, User(0, "John Doe", "password", emptyList()), "Great food!", 5, LocalDate.now())
        val restaurant = Restaurant(
            1, "Restaurant A", "", "", "", 0.0, 0.0, "", "", "",
            emptyMap(), "", emptyList(), emptySet(), emptySet(), emptyList()
        )
        dummyRestaurantRepo.addReview(review, restaurant)

        val updatedRestaurant = dummyRestaurantRepo.getRestaurant(1)

        assertEquals(4, updatedRestaurant.reviews.size)
        assertEquals(review, updatedRestaurant.reviews.last())
    }
}
