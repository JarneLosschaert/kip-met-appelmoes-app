package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.calculateAverageRating
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.getEatingOptions
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.getTodayOpeningHours
import coil.compose.rememberAsyncImagePainter



@Composable
fun BasicSpacer(height : Int = 10, width : Int = 10) {
    Spacer(modifier = Modifier
        .height(height.dp)
        .width(width.dp))
}