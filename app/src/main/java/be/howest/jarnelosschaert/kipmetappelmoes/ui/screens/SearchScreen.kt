package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.DpOffset
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.*
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.RestaurantList
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.TagList
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.BasicSpacer

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    tagsClicked: List<Tag>,
    restaurantList: List<Restaurant>,
    search: String,
    sortType: SortType,
    onRestaurantClicked: (Restaurant) -> Unit,
    onTagClicked: (Tag) -> Unit,
    onSearch: (String) -> Unit,
    onSortTypeChanged: (SortType) -> Unit
) {
    val tags = enumValues<ChildFriendliness>().toList() + enumValues<EatingOption>().toList()
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
    ) {
        var query by remember { mutableStateOf(search) }
        SearchBar(
            query = query,
            onQueryChanged = { searchQuery -> query = searchQuery },
            onSearch = onSearch,
            onSortTypeChanged = onSortTypeChanged,
            sortType = sortType
        )
        Tags(tags = tags, tagsClicked = tagsClicked, onTagClicked = onTagClicked)
        if (restaurantList.isEmpty()) {
            NothingFound()
        } else {
            RestaurantList(restaurants = restaurantList, onRestaurantClicked = onRestaurantClicked)
        }
    }
}

@Composable
fun NothingFound() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.no_restaurants_found),
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun Tags(tags: List<Tag>, tagsClicked: List<Tag>, onTagClicked: (Tag) -> Unit) {
    BasicSpacer()
    var showMore by remember { mutableStateOf(false) }
    when {
        showMore -> {
            TagList(tags = tags, all = true, clicked = tagsClicked, onTagClicked = onTagClicked)
        }
        else -> {
            TagList(tags = tags, all = false, clicked = tagsClicked, onTagClicked = onTagClicked)
        }
    }
    ExpandIcon(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { showMore = !showMore }, showMore = showMore
    )
    Divider(color = MaterialTheme.colors.primary, thickness = 2.dp)
    BasicSpacer()
}

@Composable
fun ExpandIcon(modifier: Modifier = Modifier, showMore: Boolean) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = if (showMore) R.drawable.less else R.drawable.more),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            modifier = modifier
                .width(50.dp)
        )
    }
}

@Composable
fun SearchBar(
    query: String,
    sortType: SortType,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onSortTypeChanged: (SortType) -> Unit
) {
    BasicSpacer(height = 16)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .border(2.dp, MaterialTheme.colors.primary, RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            placeholder = {
                Text(
                    text = stringResource(R.string.search),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1,
                )
            },
            modifier = Modifier
                .weight(1f)
                .height(53.dp)
                .padding(end = 16.dp),
            singleLine = true,
            maxLines = 1,
            textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                textColor = MaterialTheme.colors.onBackground
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch(query) }
            )
        )
        SortButton(onSortTypeChanged = onSortTypeChanged, sortType = sortType)
    }
}

@Composable
fun SortButton(onSortTypeChanged: (SortType) -> Unit, sortType: SortType) {
    var expanded by remember { mutableStateOf(false) }
    var currentSortType by remember { mutableStateOf(sortType) }
    Button(
        onClick = { expanded = true },
        modifier = Modifier
            .height(53.dp)
            .width(50.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.sort),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        offset = DpOffset(LocalConfiguration.current.screenWidthDp.dp - 170.dp, 5.dp),
        modifier = Modifier.background(MaterialTheme.colors.primary)
    ) {
        SortType.values().forEach { sortType ->
            var textDecoration = TextDecoration.None
            if (currentSortType == sortType) {
                textDecoration = TextDecoration.Underline
            }
            DropdownMenuItem(
                onClick = {
                    currentSortType = sortType
                    expanded = false
                    onSortTypeChanged(sortType)
                },
                modifier = Modifier
                    .width(150.dp)
            ) {
                Text(
                    text = stringResource(id = sortType.value),
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onPrimary,
                        textDecoration = textDecoration
                    )
                )
            }
        }
    }
}


@Preview
@Composable
fun SearchPreview() {
    SearchScreen(
        onRestaurantClicked = {},
        onTagClicked = {},
        onSearch = {},
        tagsClicked = listOf<Tag>(),
        restaurantList = listOf<Restaurant>(),
        search = "",
        onSortTypeChanged = {},
        sortType = SortType.DEFAULT
    )
}