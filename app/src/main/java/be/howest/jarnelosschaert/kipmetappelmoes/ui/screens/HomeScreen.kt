package be.howest.jarnelosschaert.kipmetappelmoes.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.BasicSpacer

val eatChoices = mapOf(
    "Ontbijt" to R.drawable.ontbijt,
    "Lunch" to R.drawable.lunch,
    "Diner" to R.drawable.diner,
    "Snacks" to R.drawable.snacks,
    "Drankjes" to R.drawable.drinks,
    "takeout" to R.drawable.takeout
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        BasicSpacer(height = 30)
        Title()
        BasicSpacer(height = 30)
        EatChoices()
    }
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Title() {
    Text(
        text = "Hi Jarne, waar ben je op naar zoek?",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun EatChoices() {
    LazyVerticalGrid(
        modifier = Modifier.padding(10.dp),
        columns = GridCells.Fixed(3),
        content = {
            items(eatChoices.size) { index ->
                val entry = eatChoices.entries.elementAt(index)
                val (choice, image) = entry
                EatChoice(choice, image)
            }
        }
    )
}

@Composable
fun EatChoice(choice: String, image: Int) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(110.dp)
            .padding(1.dp)
            .fillMaxWidth()
            .border(BorderStroke(2.dp, Color.Black))
            .wrapContentSize(align = Alignment.Center)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.width(70.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = choice,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview
@Composable
fun HomePreview(){
    HomeScreen()
}