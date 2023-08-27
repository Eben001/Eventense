package com.ebenezer.gana.eventense

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ebenezer.gana.eventense.navigation.Screen
import com.ebenezer.gana.eventense.presentation.screen.dashboard.event.EventScreen
import com.ebenezer.gana.eventense.presentation.screen.dashboard.profile.ProfileScreen
import com.ebenezer.gana.eventense.presentation.screen.login.LoginScreen
import com.ebenezer.gana.eventense.presentation.screen.login.LoginViewModel
import com.ebenezer.gana.eventense.ui.theme.Purple


@Composable
fun RootScreen(loginViewModel: LoginViewModel = hiltViewModel()) {

    val navigationController = rememberNavController()
    val isLoggedIn = loginViewModel.signedInState.value

    Scaffold(bottomBar = {
        if (isLoggedIn) {
            BottomBar(navigationController)
        }
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navigationController,
                startDestination = if (isLoggedIn) {
                    NavigationItem.Event.route
                } else {
                    Screen.Login.route
                }
            ) {
                composable(Screen.Login.route) {
                    LoginScreen(navigationController) {

                    }
                }
                composable(NavigationItem.Event.route) {
                    EventScreen()
                }

                composable(NavigationItem.Profile.route) {
                    ProfileScreen(navController = navigationController)
                }
            }
        }


    }
}


sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Event : NavigationItem(Screen.Event.route, Icons.Filled.Event, "Event")
    object Profile : NavigationItem(Screen.Profile.route, Icons.Filled.Person, "Profile")
}


@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Event,
        NavigationItem.Profile
    )

    BottomNavigation(
        backgroundColor = Purple,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Image(
                        imageVector = item.icon, contentDescription = item.title,
                        modifier = Modifier.size(30.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
                alwaysShowLabel = false,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}