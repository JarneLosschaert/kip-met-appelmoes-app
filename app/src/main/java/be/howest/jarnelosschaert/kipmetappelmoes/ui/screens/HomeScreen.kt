package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.howest.jarnelosschaert.kipmetappelmoes.R
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.EatingOption
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.components.BasicSpacer

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onEatingOptionClicked: (EatingOption) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        BasicSpacer(height = 30)
        Welcome()
        BasicSpacer(height = 30)
        EatingOptions(onEatingOptionClicked = onEatingOptionClicked)
    }
}

@Composable
fun Logo() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.secondaryVariant),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.chicken),
            contentDescription = "Logo",
            modifier = Modifier
                .height(250.dp)
                .padding(15.dp)
        )
    }
}

@Composable
fun Welcome() {
    Text(
        text = stringResource(R.string.welcome_text),
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun EatingOptions(onEatingOptionClicked: (EatingOption) -> Unit) {
    val eatingOptions = enumValues<EatingOption>()
    LazyVerticalGrid(
        modifier = Modifier.padding(10.dp),
        columns = GridCells.Fixed(3),
        content = {
            items(eatingOptions.size) { index ->
                EatingOption(eatingOption = eatingOptions[index], onEatingOptionClicked = onEatingOptionClicked)
            }
        }
    )
}

@Composable
fun EatingOption(eatingOption: EatingOption, onEatingOptionClicked: (EatingOption) -> Unit) {
    val choice = stringResource(id = eatingOption.value)
    val image = eatingOption.image
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentHeight()
        .padding(1.dp)
        .fillMaxWidth()
        .border(BorderStroke(2.dp, Color.Black))
        .clickable { onEatingOptionClicked(eatingOption) }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
        ) {

            BasicSpacer(height = 20)
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .width(70.dp)
                    .align(Alignment.CenterHorizontally)
            )
            BasicSpacer()
            Text(
                text = choice,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            BasicSpacer()
        }
    }
}


@Preview
@Composable
fun HomePreview(){
    HomeScreen()
}