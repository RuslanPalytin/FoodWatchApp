package com.example.testwatchapp.presentation.sealed

sealed class RootScreens(val route: String) {
    object SplashScreen : RootScreens(route = "first_screen")
    object SignInScreen : RootScreens(route = "second_screen")
    object OrderScreen : RootScreens(route = "order_screen")
    object MapScreen : RootScreens(route = "map_screen")
}
