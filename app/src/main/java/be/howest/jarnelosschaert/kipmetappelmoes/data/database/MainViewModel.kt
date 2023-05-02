package be.howest.jarnelosschaert.test.data

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
    val searchResults: MutableLiveData<List<User>>


    init {
        val productDb = UserRoomDatabase.getInstance(application)
        val productDao = productDb.userDao()
        repository = UserRepo(productDao)

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