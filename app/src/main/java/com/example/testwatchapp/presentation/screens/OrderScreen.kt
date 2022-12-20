package com.example.testwatchapp.presentation.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testwatchapp.R
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import com.example.testwatchapp.presentation.api.ApiService
import com.example.testwatchapp.presentation.model.DishesGetModel
import com.example.testwatchapp.presentation.model.OrderGetModel
import com.example.testwatchapp.presentation.storage.Token
import com.example.testwatchapp.presentation.theme.Nutino
import com.example.testwatchapp.presentation.theme.Orange1
import com.example.testwatchapp.presentation.theme.Roboto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun OrderScreen(navController: NavHostController) {

    val listOrder = remember { mutableStateOf<List<OrderGetModel>?>(null) }
    val context = LocalContext.current

    getHistoryFoodsApi(context, listOrder)
    Log.d("MyLog", listOrder.value.toString())

    if (listOrder.value != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Orange1)
                .padding(horizontal = 10.dp)
                .padding(top = 5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(
                        text = listOrder.value?.get(0)!!.address,
                        fontFamily = Roboto,
                        fontSize = 10.sp,
                        color = Color.Black
                    )
                    Text(
                        text = listOrder.value?.get(0)!!.date,
                        fontFamily = Roboto,
                        fontSize = 10.sp,
                        color = Color.Black
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_navigation),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            ListFoods(listOrder.value?.get(4)!!.dishes)
        }
    }
}

@Composable
fun ListFoods(listOrder: MutableList<DishesGetModel>) {
    LazyColumn(
        content = {
            itemsIndexed(listOrder) { index, item ->
                Item(item = item)
            }
        }
    )
}

@Composable
fun Item(item: DishesGetModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(color = Orange1)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color = Orange1)
                    .padding(0.dp),
            ) {
                AsyncImage(
                    model = item.dish.icon,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Orange1)
                        .clip(shape = CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.dish.name,
                    fontFamily = Nutino,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    text = item.count + " prices",
                    textAlign = TextAlign.Center,
                    fontFamily = Nutino,
                    fontSize = 8.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}

fun getHistoryFoodsApi(context: Context, result: MutableState<List<OrderGetModel>?>) {

    val token = Token(context).readToken()

    ApiService.retrofit.getOrdersHistory(token = "Bearer $token")!!
        .enqueue(object : Callback<List<OrderGetModel>?> {
            override fun onResponse(
                call: Call<List<OrderGetModel>?>,
                response: Response<List<OrderGetModel>?>
            ) {
                if (response.isSuccessful) {
                    result.value = response.body()
                } else {
                    Toast.makeText(context, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<OrderGetModel>?>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
}