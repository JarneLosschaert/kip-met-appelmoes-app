package be.howest.jarnelosschaert.kipmetappelmoes.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.User
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.SortType
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Tag

class UiState : ViewModel() {
    var restaurantClicked by mutableStateOf(Restaurant( 0,"","", "", "",0.0,0.0, "", "","", mapOf(), "", listOf(), setOf(), setOf(), listOf()))
    var tagsClicked by mutableStateOf(emptyList<Tag>())
    var search by mutableStateOf("")
    var sortType by mutableStateOf(SortType.DEFAULT)
    var currentUser by mutableStateOf(User(-1, "", "", listOf()))

    fun addTag(tag: Tag) {
        if (!tagsClicked.contains(tag)) {
            tagsClicked = tagsClicked + tag
        }
    }
    fun removeTag(tag: Tag) {
        tagsClicked = tagsClicked - tag
    }
}
