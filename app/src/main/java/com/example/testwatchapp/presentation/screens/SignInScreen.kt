package com.example.testwatchapp.presentation.screens

import android.content.Context
import android.widget.MultiAutoCompleteTextView.Tokenizer
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import com.example.testwatchapp.presentation.api.ApiService
import com.example.testwatchapp.presentation.model.TokenModel
import com.example.testwatchapp.presentation.model.UserLoginModel
import com.example.testwatchapp.presentation.sealed.RootScreens
import com.example.testwatchapp.presentation.storage.Token
import com.example.testwatchapp.presentation.theme.Nutino
import com.example.testwatchapp.presentation.theme.Orange1
import com.example.testwatchapp.presentation.theme.Orange2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.format.TextStyle
import kotlin.math.log

@Composable
fun SecondScreen(navController: NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange1)
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp)
    ) {
        Text(text = "E-mail", color = Color.Gray, fontSize = 10.sp, fontFamily = Nutino)
        Spacer(modifier = Modifier.height(10.dp))
        BasicTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Password", color = Color.Gray, fontSize = 10.sp, fontFamily = Nutino)
        Spacer(modifier = Modifier.height(10.dp))
        BasicTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(33.dp)
                .clip(shape = RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Orange2),
            onClick = {

                val loginUser = UserLoginModel(
                    email = email,
                    password = password
                )
                loginUser(loginModel = loginUser, context = context, navController = navController)
            },
        ) {
            Text(text = "Sign In", fontFamily = Nutino, color = Color.White, fontSize = 14.sp)
        }
    }
}

fun loginUser(
    loginModel: UserLoginModel,
    context: Context,
    navController: NavHostController
) {

    val token = Token(context)

    ApiService.retrofit.loginUser(loginModel).enqueue(object : Callback<TokenModel> {
        override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
            if (response.isSuccessful) {
                token.saveToken(response.body()?.access_token!!)
                navController.navigate(RootScreens.OrderScreen.route)
            } else {
                Toast.makeText(context, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<TokenModel>, t: Throwable) {
            Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
        }
    })
}