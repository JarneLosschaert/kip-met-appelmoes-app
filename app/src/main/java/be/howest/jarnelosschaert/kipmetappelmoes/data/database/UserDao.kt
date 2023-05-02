package be.howest.jarnelosschaert.test.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :name")
    fun findUser(name: String): List<User>

    @Query("UPDATE users SET favorites = :favorites WHERE username = :username")
    fun updateUser(username: String, favorites: List<Int>)

    @Query("DELETE FROM users WHERE username = :name")
    fun deleteUser(name: String)

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>
}