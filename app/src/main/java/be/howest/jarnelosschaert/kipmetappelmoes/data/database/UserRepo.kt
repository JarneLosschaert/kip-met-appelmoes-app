package be.howest.jarnelosschaert.kipmetappelmoes.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.User
import be.howest.jarnelosschaert.test.data.UserDao
import kotlinx.coroutines.*

class UserRepo(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()
    val searchResults = MutableLiveData<List<User>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertUser(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.insertUser(newUser)
        }
    }

    fun updateUser(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.updateUser(username = newUser.username, favorites = newUser.favorites)
        }
    }

    fun deleteUser(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.deleteUser(name)
        }
    }

    fun findUser(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<User>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async userDao.findUser(name)
        }
}