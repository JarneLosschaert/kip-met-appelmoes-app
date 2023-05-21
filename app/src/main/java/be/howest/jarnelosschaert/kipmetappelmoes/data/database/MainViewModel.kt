package be.howest.jarnelosschaert.kipmetappelmoes.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.User
import be.howest.jarnelosschaert.kipmetappelmoes.data.database.UserRepo
import be.howest.jarnelosschaert.kipmetappelmoes.data.database.UserRoomDatabase


class MainViewModel(application: Application) : ViewModel() {

    val allUsers: LiveData<List<User>>
    private val repository: UserRepo
    private val searchResults: MutableLiveData<List<User>>

    init {
        val userDb = UserRoomDatabase.getInstance(application)
        val userDao = userDb.userDao()
        repository = UserRepo(userDao)

        allUsers = repository.allUsers
        searchResults = repository.searchResults
    }

    fun insertUser(user: User) {
        repository.insertUser(user)
    }

    fun updateUser(user: User) {
        repository.updateUser(user)
    }

    fun findUser(name: String) {
        return repository.findUser(name)
    }

    fun deleteUser(name: String) {
        repository.deleteUser(name)
    }

}