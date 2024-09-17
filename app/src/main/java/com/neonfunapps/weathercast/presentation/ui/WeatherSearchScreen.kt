package com.neonfunapps.weathercast.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.neonfunapps.weathercast.presentation.WeatherViewModel

@Composable
fun SearchScreen(viewModel: WeatherViewModel, navController: NavHostController) {
    var query by remember { mutableStateOf("") }

    Column {
        TextField(value = query, onValueChange = { query = it }, label = { Text("Search") })

        Button(onClick = {
            if (query.isNotEmpty()) {
                val cityName = query.lowercase().replaceFirstChar { it.uppercase() }
                navController.navigate("details/$cityName")
                viewModel.loadWeatherInfo()
            }
        }) {
            Text("Search")
        }
    }
}
