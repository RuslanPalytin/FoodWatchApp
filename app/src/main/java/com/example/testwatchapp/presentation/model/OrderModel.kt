package com.example.testwatchapp.presentation.model

data class OrderModel(
    val address: String,
    val date: String,
    val totalPrice: String,
    val dishes: MutableList<Dishes>,
)

data class Dishes(
    val id: String,
    val count: String,
)
