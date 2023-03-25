package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.DataSource.restaurants
import java.util.*
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
import androidx.compose.ui.text.input.ImeAction
import be.howest.jarnelosschaert.kipmetappelmoes.data.ChildFriendliness
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.RestaurantList
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.TagList

val tags = enumValues<ChildFriendliness>().toList().map { it.name.lowercase(Locale.ROOT) }

@Composable
fun SearchScreen(modifier : Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()) {
        var query by remember { mutableStateOf("") }
        SearchBar(
            query = query,
            onQueryChanged = {searchQuery -> query = searchQuery},
            onSearch = {}
        )
        Tags(tags = tags)
        RestaurantList(restaurants = restaurants)
    }
}

@Composable
fun Tags(tags: List<String>) {
    BasicSpacer()
    var showMore by remember { mutableStateOf(false) }
    when {
        showMore -> {
            TagList(tags = tags, all = true)
        }
        else -> {
            TagList(tags = tags, all = false)
        }
    }
    ExpandIcon(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { showMore = !showMore }, showMore= showMore)
    Divider( color = MaterialTheme.colors.primary, thickness = 2.dp)
    BasicSpacer()
}

@Composable
fun ExpandIcon(modifier: Modifier = Modifier, showMore : Boolean) {
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
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
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
            placeholder = { Text("Search") },
            modifier = Modifier
                .weight(1f)
                .height(53.dp)
                .padding(end = 16.dp),
            singleLine = true,
            maxLines = 1,
            textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                textColor = MaterialTheme.colors.onSurface
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }
            )
        )
        Button(
            onClick = { /* handle button click */ },
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
    }
}

@Preview
@Composable
fun SearchPreview() {
    SearchScreen()
}