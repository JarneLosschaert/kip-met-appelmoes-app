package be.howest.jarnelosschaert.kipmetappelmoes.ui

import be.howest.jarnelosschaert.kipmetappelmoes.data.models.User
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.uiState
import be.howest.jarnelosschaert.test.data.MainViewModel

class UserController(viewModel: MainViewModel) {

    private val userViewModel = viewModel
    private val allUsers = viewModel.allUsers

    fun registerUser(username: String, password: String) {
        val user: User = User(0, username, password, listOf())
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




}