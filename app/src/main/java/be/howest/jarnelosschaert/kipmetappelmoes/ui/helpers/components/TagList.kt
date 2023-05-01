package be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Tag
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@Composable
fun TagList(tags: List<Tag>, all: Boolean = true, clicked : List<Tag> = listOf(), onTagClicked: (Tag) -> Unit = {}) {
    val filteredTags = tags.filter { !clicked.contains(it) }
    val sortedTags = clicked + filteredTags
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        contentAlignment = Alignment.Center
    ) {

    }
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        crossAxisSpacing = 5.dp,
        mainAxisSpacing = 8.dp,

    ) {
        var max = tags.size
        if (!all) {
            max = 6
        }
        for (i in 0 until max) {
            var color = MaterialTheme.colors.secondary
            if (clicked.contains(sortedTags[i])) {
                color = MaterialTheme.colors.secondaryVariant
            }
            Tag(text = stringResource(id = sortedTags[i].value), color = color, onTagClicked = onTagClicked, tag = sortedTags[i])
        }
    }
}

@Composable
fun Tag(text: String, color: Color, onTagClicked: (Tag) -> Unit, tag: Tag) {
    Text(
        text = text,
        modifier = Modifier
            .background(
                color = color,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp)
            .clickable { onTagClicked(tag) },
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        color = MaterialTheme.colors.onSecondary
    )
}
