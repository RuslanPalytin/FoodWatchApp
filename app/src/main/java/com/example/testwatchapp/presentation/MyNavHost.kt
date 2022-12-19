package com.example.testwatchapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testwatchapp.presentation.screens.MapScreen
import com.example.testwatchapp.presentation.screens.OrderScreen
import com.example.testwatchapp.presentation.screens.SplashScreen
import com.example.testwatchapp.presentation.screens.SecondScreen
import com.example.testwatchapp.presentation.sealed.RootScreens

@Composable
fun MyNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = RootScreens.SplashScreen.route) {
        composable(RootScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(RootScreens.SignInScreen.route) {
            SecondScreen(navController)
        }
        composable(RootScreens.OrderScreen.route) {
            OrderScreen(navController)
        }
        composable(RootScreens.MapScreen.route) {
            MapScreen(navController)
        }
    }
}