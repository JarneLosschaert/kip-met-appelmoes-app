package be.howest.jarnelosschaert.kipmetappelmoes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import be.howest.jarnelosschaert.kipmetappelmoes.ui.HomePreview
import be.howest.jarnelosschaert.kipmetappelmoes.ui.HomeScreen
import be.howest.jarnelosschaert.kipmetappelmoes.ui.RestaurantPreview
import be.howest.jarnelosschaert.kipmetappelmoes.ui.SearchPreview
import be.howest.jarnelosschaert.kipmetappelmoes.ui.theme.KipMetAppelmoesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KipMetAppelmoesTheme {
                // A surface container using the 'background' color from the theme
                KipMetAppelmoesApp()
            }
        }
    }
}