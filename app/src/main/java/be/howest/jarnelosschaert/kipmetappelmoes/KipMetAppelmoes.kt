package be.howest.jarnelosschaert.kipmetappelmoes

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import be.howest.jarnelosschaert.kipmetappelmoes.ui.HomeScreen
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.FavoritesScreen
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.MapScreen
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.SearchScreen

sealed class BottomNavigationScreens(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavigationScreens("home", "Home", Icons.Filled.Home)
    object Search : BottomNavigationScreens("search", "Search", Icons.Filled.Search)
    object Favorites : BottomNavigationScreens("favorites", "Favorites", Icons.Filled.Favorite)
    object Map : BottomNavigationScreens("map", "Map", Icons.Filled.LocationOn)
}

@Composable
fun KipMetAppelmoesApp() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Search,
        BottomNavigationScreens.Favorites,
        BottomNavigationScreens.Map
    )

    Scaffold(
        bottomBar = {
            KipMetAppelmoesBottomNavigation(navController, bottomNavigationItems)
        },
        content = { innerPadding ->
            MainScreenNavigationConfigurations(navController, Modifier.padding(innerPadding))
        }
    )
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route) {
        composable(BottomNavigationScreens.Home.route) {
            HomeScreen(modifier)
        }
        composable(BottomNavigationScreens.Search.route) {
            SearchScreen(modifier)
        }
        composable(BottomNavigationScreens.Favorites.route) {
            FavoritesScreen(modifier)
        }
        composable(BottomNavigationScreens.Map.route) {
            MapScreen(modifier)
        }
    }
}

@Composable
fun KipMetAppelmoesBottomNavigation(navController: NavHostController, items: List<BottomNavigationScreens>) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null)},
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString("key")
}




