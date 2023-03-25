package be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.data.EatingOptions
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.BasicSpacer
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.ExpandIcon
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@Composable
fun TagList(tags: List<String>, all : Boolean = true) {
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
            Text(
                text = tags[i],
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.secondary,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Composable
fun Tag(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier
            .background(MaterialTheme.colors.secondary, RoundedCornerShape(4.dp))
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    )
}
