package be.howest.jarnelosschaert.kipmetappelmoes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var id: Int = 0,
    val username: String,
    val password: String,
    var favorites: List<Int>
)