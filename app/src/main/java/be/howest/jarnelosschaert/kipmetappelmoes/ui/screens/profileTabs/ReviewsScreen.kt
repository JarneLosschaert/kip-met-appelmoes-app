package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Review
import be.howest.jarnelosschaert.kipmetappelmoes.data.repos.ApiServiceClient
import be.howest.jarnelosschaert.kipmetappelmoes.data.repos.ReviewRetro
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.BasicSpacer
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.Stars
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.TitleProfileTab
import be.howest.jarnelosschaert.kipmetappelmoes.uiState

@Composable
fun ReviewsScreen(modifier: Modifier = Modifier, reviews: List<ReviewRetro>, onGoBack: () -> Unit = {}) {
    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        TitleProfileTab(title = stringResource(R.string.title_ratings), onGoBack = onGoBack)
        if (uiState.currentUser.id == -1) {
            stringResource(R.string.loginfirst, stringResource(R.string.ratings))
        }
        ReviewList(reviews = reviews)
    }
}

@Composable
fun ReviewList(reviews: List<ReviewRetro>) {
    BasicSpacer(20)
    LazyColumn {
        items(reviews.size) { index ->
            val review = reviews[index]
            Row() {
                Stars(amount = review.Rating)
            }
            Text(text = review.Date)
            Text(review.Content)
            BasicSpacer(height = 15)
        }
    }
}

@Preview
@Composable
fun ReviewsPreview() {
    ReviewsScreen( reviews = listOf())
}
