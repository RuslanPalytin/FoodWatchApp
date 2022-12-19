package com.example.testwatchapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Text
import com.example.testwatchapp.R
import com.example.testwatchapp.presentation.sealed.RootScreens
import com.example.testwatchapp.presentation.theme.Italiano
import com.example.testwatchapp.presentation.theme.Orange1
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Orange1),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(130.dp).padding(top = 10.dp),
            painter = painterResource(id = R.drawable.ic_logo_watch),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "WSR Food",
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontFamily = Italiano,
            fontSize = 30.sp
        )
    }

    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate(RootScreens.SignInScreen.route)
    }
}