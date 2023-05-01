package be.howest.jarnelosschaert.kipmetappelmoes.ui.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.*


@Composable
fun MapScreen(modifier: Modifier = Modifier, restaurants: List<Restaurant>) {
    var granted by remember { mutableStateOf(false) }
    HandlePermissions(onPermissionGranted = {
        granted = it
    })
    Map(isMyLocationEnabled = granted, modifier = modifier, restaurants = restaurants)
}

@Composable
fun Map(
    modifier: Modifier = Modifier,
    isMyLocationEnabled: Boolean = true,
    restaurants: List<Restaurant>
) {
    val cameraPositionState = rememberCameraPositionState()
    val brugesBounds = remember {
        LatLngBounds(
            LatLng(51.1832, 3.1597),
            LatLng(51.2396, 3.2883)
        )
    }
    val properties = MapProperties(
        isMyLocationEnabled = isMyLocationEnabled,
        latLngBoundsForCameraTarget = brugesBounds,
        maxZoomPreference = 21.0f,
        minZoomPreference = 13f
    )
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
    ) {
        restaurants.forEach { restaurant ->
            Marker(
                state = MarkerState(LatLng(restaurant.latitude, restaurant.longitude)),
                title = restaurant.name,
                snippet = restaurant.address
            )
        }
    }
}


@Composable
fun HandlePermissions(onPermissionGranted: (Boolean) -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onPermissionGranted(true)
        } else {
            onPermissionGranted(false)
        }
    }

    if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
        == android.content.pm.PackageManager.PERMISSION_GRANTED) {
        onPermissionGranted(true)
    } else {
        LaunchedEffect(true) {
            launcher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}





@Preview
@Composable
fun MapPreview() {
    // MapScreen()
}