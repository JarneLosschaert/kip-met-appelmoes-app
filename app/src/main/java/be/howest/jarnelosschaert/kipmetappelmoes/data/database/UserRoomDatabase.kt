package be.howest.jarnelosschaert.kipmetappelmoes.data.database

import android.content.Context
import androidx.room.*
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.User
import be.howest.jarnelosschaert.test.data.UserDao

@Database(entities = [(User::class)], version = 2)
@TypeConverters(Converters::class)
abstract class UserRoomDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var INSTANCE: UserRoomDatabase? = null

        fun getInstance(context: Context): UserRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserRoomDatabase::class.java,
                        "user_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}