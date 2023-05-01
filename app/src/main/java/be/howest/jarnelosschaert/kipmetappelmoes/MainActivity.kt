package be.howest.jarnelosschaert.kipmetappelmoes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.HandlePermissions
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.RestaurantPreview
import be.howest.jarnelosschaert.kipmetappelmoes.ui.theme.KipMetAppelmoesTheme
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.HandleNotifications

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KipMetAppelmoesTheme {
                KipMetAppelmoesApp()
                HandleNotifications()
            }
        }
    }
}