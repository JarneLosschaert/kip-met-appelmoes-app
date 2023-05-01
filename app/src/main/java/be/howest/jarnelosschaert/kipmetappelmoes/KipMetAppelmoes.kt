package be.howest.jarnelosschaert.kipmetappelmoes

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import be.howest.jarnelosschaert.kipmetappelmoes.data.UiState
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.EatingOption
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Restaurant
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.SortType
import be.howest.jarnelosschaert.kipmetappelmoes.data.models.Tag
import be.howest.jarnelosschaert.kipmetappelmoes.ui.RestaurantController
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.HomeScreen
import be.howest.jarnelosschaert.kipmetappelmoes.ui.helpers.getSortedRestaurants
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.*
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs.AccountScreen
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs.FavoritesScreen
import be.howest.jarnelosschaert.kipmetappelmoes.ui.screens.profileTabs.SettingsScreen

sealed class BottomNavigationScreens(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavigationScreens("home", "Home", Icons.Filled.Home)
    object Search : BottomNavigationScreens("search", "Zoek", Icons.Filled.Search)
    object Map : BottomNavigationScreens("map", "Kaart", Icons.Filled.LocationOn)
    object Profile : BottomNavigationScreens("profile", "Profiel", Icons.Filled.AccountCircle)
}

sealed class OtherScreens(val route: String) {
    object Restaurant : OtherScreens("restaurant")
    object Account : OtherScreens("account")
    object Favorites : OtherScreens("favorites")
    object Reviews : OtherScreens("reviews")
    object Settings : OtherScreens("settings")
}

val uiState = UiState()
val controller = RestaurantController()


@Composable
fun KipMetAppelmoesApp() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Search,
        BottomNavigationScreens.Map,
        BottomNavigationScreens.Profile
    )

    var pageClicked by remember { mutableStateOf(BottomNavigationScreens.Home.route) }

    Scaffold(
        bottomBar = {
            KipMetAppelmoesBottomNavigation(navController, bottomNavigationItems, pageClicked)
        },
        content = { innerPadding ->
            MainScreenNavigationConfigurations(
                navController,
                Modifier.padding(innerPadding),
                onNavigation = { pageClicked = it })
        }
    )
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onNavigation: (String) -> Unit
) {
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route) {
        composable(BottomNavigationScreens.Home.route) {
            HomeScreen(modifier = modifier, onEatingOptionClicked = { eatingOption: EatingOption ->
                uiState.tagsClicked = listOf(eatingOption)
                navController.navigate(BottomNavigationScreens.Search.route)
            })
            onNavigation(BottomNavigationScreens.Home.route)
        }
        composable(BottomNavigationScreens.Search.route) {
            SearchScreen(modifier = modifier,
                tagsClicked = uiState.tagsClicked,
                search = uiState.search,
                sortType = uiState.sortType,
                restaurantList = getSortedRestaurants(
                    controller.getRestaurants(
                        uiState.tagsClicked,
                        uiState.search
                    ), uiState.sortType
                ),
                onRestaurantClicked = { restaurant: Restaurant ->
                    uiState.restaurantClicked = restaurant
                    navController.navigate(OtherScreens.Restaurant.route)
                },
                onTagClicked = { tag: Tag ->
                    if (uiState.tagsClicked.contains(tag)) {
                        uiState.removeTag(tag)
                    } else {
                        uiState.addTag(tag)
                    }
                },
                onSearch = { search: String ->
                    uiState.search = search
                },
                onSortTypeChanged = { sortType: SortType ->
                    uiState.sortType = sortType
                }
            )
            onNavigation(BottomNavigationScreens.Search.route)
        }
        composable(BottomNavigationScreens.Profile.route) {
            ProfileScreen(modifier = modifier,
                onScreenChange = { screen: String ->
                    navController.navigate(screen)
                })
            onNavigation(BottomNavigationScreens.Profile.route)
        }
        composable(BottomNavigationScreens.Map.route) {
            MapScreen(modifier = modifier, restaurants = controller.getAllRestaurants())
            onNavigation(BottomNavigationScreens.Map.route)
        }
        composable(OtherScreens.Restaurant.route) {
            RestaurantScreen(modifier = modifier, restaurant = uiState.restaurantClicked, onReviewSubmitted = {
                uiState.restaurantClicked = controller.getRestaurant(uiState.restaurantClicked.id)
            },
                onGoBack = {
                    navController.navigate(BottomNavigationScreens.Search.route)
                }
            )
        }
        composable(OtherScreens.Account.route) {
            AccountScreen(modifier = modifier, onGoBack = {
                navController.navigate(BottomNavigationScreens.Profile.route)
            })
        }
        composable(OtherScreens.Favorites.route) {
            FavoritesScreen(modifier = modifier,
                onRestaurantClicked = { restaurant: Restaurant ->
                    uiState.restaurantClicked = restaurant
                    navController.navigate(OtherScreens.Restaurant.route)
                }, onGoBack = {
                    navController.navigate(BottomNavigationScreens.Profile.route)
                }
            )
        }
        composable(OtherScreens.Reviews.route) {
            ReviewsScreen(modifier = modifier, onGoBack = {
                navController.navigate(BottomNavigationScreens.Profile.route)
            })
        }
        composable(OtherScreens.Settings.route) {
            SettingsScreen(modifier = modifier, onGoBack = {
                navController.navigate(BottomNavigationScreens.Profile.route)
            })
        }
    }
}

@Composable
fun KipMetAppelmoesBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>,
    pageClicked: String
) {

    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            var color = Color.LightGray
            if (pageClicked == screen.route) {
                color = Color.White
            }
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null, tint = color) },
                label = { Text(screen.label, color = color) },
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
    return navBackStackEntry?.arguments?.getString("")
}






