package com.ebenezer.gana.eventense.navigation

sealed class Screen(val route:String){
    object Login: Screen(route = "login_screen")
    object Event: Screen(route = "event_screen")
    object Profile: Screen(route = "profile_screen")
}
