package com.neonfunapps.weathercast.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neonfunapps.weathercast.R
import com.neonfunapps.weathercast.domain.weather.WeatherInfo
import com.neonfunapps.weathercast.presentation.WeatherViewModel

@Composable
fun WeatherDetailsScreen(viewModel: WeatherViewModel, city: String?) {
    LaunchedEffect(city) {
        city?.let { viewModel.getCoordinatesForCity(it) }
    }

    val weatherInfo = remember { viewModel.state.weatherInfo }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF3389F1), Color(0xFFD2E9FF)
                    )
                )
            )
            .verticalScroll(scrollState)
            .padding(16.dp, 32.dp, 16.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DateAndCity(weatherInfo, city)
        Spacer(modifier = Modifier.height(16.dp))

        WeatherIconAndDescription(weatherInfo)
        Spacer(modifier = Modifier.height(16.dp))

        Temperature(weatherInfo)
        Spacer(modifier = Modifier.height(24.dp))

        WeatherDetails(weatherInfo)
        Spacer(modifier = Modifier.height(24.dp))

        // ToDo - add graph with temperatures for next hours
        // ToDo - Add forecast for next days
    }
}

@Composable
fun DateAndCity(weatherInfo: WeatherInfo?, city: String?) {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = weatherInfo?.currentWeatherData?.formattedDate ?: "",
            fontSize = 18.sp,
            color = Color.White
        )
        Text(
            text = city ?: "", fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun WeatherIconAndDescription(weatherInfo: WeatherInfo?) {
    Image(
        painter = painterResource(
            weatherInfo?.currentWeatherData?.weatherType?.iconRes ?: R.drawable.ic_sun
        ), contentDescription = null, modifier = Modifier.size(300.dp)
    )
    Text(
        text = weatherInfo?.currentWeatherData?.weatherType?.weatherDesc ?: "",
        fontSize = 32.sp,
        color = Color.White,
        fontWeight = FontWeight.Light
    )
}

@Composable
fun Temperature(weatherInfo: WeatherInfo?) {
    val temperatureCelsius =
        weatherInfo?.currentWeatherData?.temperatureCelsius?.substring(0, 2)?.toInt() ?: 0
    val textColor = when {
        temperatureCelsius < 10 -> Color.Blue
        temperatureCelsius in 10..20 -> Color.Gray
        else -> Color.Red
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = weatherInfo?.currentWeatherData?.temperatureCelsius.toString(),
            fontSize = 64.sp,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = "feels like", fontSize = 18.sp, color = Color.White
            )
            Text(
                text = weatherInfo?.currentWeatherData?.apparentTemperature.toString(),
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun WeatherDetails(weatherInfo: WeatherInfo?) {
    Column(modifier = Modifier.fillMaxWidth()) {
        DetailRow(
            label1 = "Humidity",
            value1 = weatherInfo?.currentWeatherData?.humidity.toString(),
            label2 = "Pressure",
            value2 = weatherInfo?.currentWeatherData?.pressure.toString()
        )
        WeatherTableHorizontalDivider()
        DetailRow(
            label1 = "Rain",
            value1 = weatherInfo?.currentWeatherData?.rain.toString(),
            label2 = "Wind",
            value2 = weatherInfo?.currentWeatherData?.windSpeed.toString()
        )
    }
}

@Composable
fun DetailRow(label1: String, value1: String, label2: String, value2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        DetailColumn(
            label = label1, value = value1,
            Modifier
                .weight(1f)
                .padding(8.dp)
        )
        WeatherTableVerticalDivider()
        DetailColumn(
            label = label2, value = value2,
            Modifier
                .weight(1f)
                .padding(8.dp)
        )
    }
}

@Composable
fun DetailColumn(label: String, value: String, modifier: Modifier) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, color = Color.White, fontSize = 16.sp)
        Text(
            text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp
        )
    }
}

@Composable
fun WeatherTableVerticalDivider() {
    VerticalDivider(
        color = Color.White, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
    )
}

@Composable
fun WeatherTableHorizontalDivider() {
    HorizontalDivider(
        color = Color.White, thickness = 1.dp
    )
}