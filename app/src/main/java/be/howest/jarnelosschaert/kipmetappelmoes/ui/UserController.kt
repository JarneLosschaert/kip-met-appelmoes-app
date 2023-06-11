package be.howest.jarnelosschaert.kipmetappelmoes.ui

import android.util.Log
import androidx.compose.runtime.*
import be.howest.jarnelosschaert.kipmetappelmoes.data.database.MainViewModel
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.User
import be.howest.jarnelosschaert.kipmetappelmoes.data.repos.ApiServiceClient
import be.howest.jarnelosschaert.kipmetappelmoes.data.repos.ReviewRetro
import be.howest.jarnelosschaert.kipmetappelmoes.uiState

class UserController(viewModel: MainViewModel) {

    private val userViewModel = viewModel
    private val allUsers = viewModel.allUsers
    fun registerUser(username: String, password: String) {
        val user = User(0, username, password, listOf())
        userViewModel.insertUser(user)
    }

    fun loginUser(username: String, password: String): Boolean {
        val user: User = allUsers.value?.find { user -> user.username == username } ?: return false
        return user.password == password
    }

    fun getUser(username: String): User {
        return allUsers.value?.find { user -> user.username == username } ?: User(0, "", "", listOf())
    }

    fun addFavorite(restaurant: Restaurant) {
        uiState.currentUser.favorites += restaurant.id
        userViewModel.updateUser(uiState.currentUser)
    }

    fun removeFavorite(restaurant: Restaurant) {
        uiState.currentUser.favorites -= restaurant.id
        userViewModel.updateUser(uiState.currentUser)
    }

    @Composable
    fun getReviews(): List<ReviewRetro> {
        var reviews by remember { mutableStateOf(emptyList<ReviewRetro>()) }
        LaunchedEffect(Unit) {
            try {
                reviews = ApiServiceClient.apiService.getReviews()
                reviews = reviews.filter { review -> review.UserId == uiState.currentUser.id }
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
        }
        return reviews
    }
}