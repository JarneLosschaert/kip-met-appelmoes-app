package be.howest.jarnelosschaert.kipmetappelmoes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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