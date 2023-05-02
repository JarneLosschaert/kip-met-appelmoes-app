package be.howest.jarnelosschaert.kipmetappelmoes

import android.app.Application
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.HandleNotifications
import be.howest.jarnelosschaert.test.data.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KipMetAppelmoesTheme {

                val owner = LocalViewModelStoreOwner.current

                owner?.let {
                    val viewModel: MainViewModel = viewModel(
                        it,
                        "MainViewModel",
                        MainViewModelFactory(
                            LocalContext.current.applicationContext
                                    as Application
                        )
                    )
                    KipMetAppelmoesApp(viewModel)
                }
            }
        }
    }
}

class MainViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }
}