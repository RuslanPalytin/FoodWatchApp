package com.example.testwatchapp.presentation.api

import com.example.testwatchapp.presentation.model.OrderGetModel
import com.example.testwatchapp.presentation.model.OrderModel
import com.example.testwatchapp.presentation.model.TokenModel
import com.example.testwatchapp.presentation.model.UserLoginModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("api/auth/login")
    fun loginUser(
        @Body loginModel: UserLoginModel
    ): Call<TokenModel>

    @GET("api/delivery/orders/histories")
    fun getOrdersHistory(
        @Header ("Authorization") token: String
    ): Call<List<OrderGetModel>?>?
}