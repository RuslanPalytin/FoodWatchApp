package com.example.testwatchapp.presentation.api

import com.example.testwatchapp.presentation.model.TokenModel
import com.example.testwatchapp.presentation.model.UserLoginModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("api/auth/login")
    fun loginUser(
        @Body loginModel: UserLoginModel
    ): Call<TokenModel>
}