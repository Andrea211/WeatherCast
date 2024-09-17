package com.neonfunapps.weathercast.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neonfunapps.weathercast.domain.repository.WeatherRepository
import com.neonfunapps.weathercast.domain.util.Resource
import com.neonfunapps.weathercast.domain.location.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun getCoordinatesForCity(city: String) {
        viewModelScope.launch {
            state = state.copy(
                city = city,
            )
            repository.getCityCoordinates(city).let { coordinates ->
                coordinates.data?.let { data ->
                    state = state.copy(
                        city = city,
                        cityCoordinates = Pair(data.latitude, data.longitude),
                    )
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve city coordinates."
                )
            }
        }
    }

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null,
            )

            if (state.cityCoordinates != null) {
                when (val result = repository.getWeatherData(state.cityCoordinates!!.first, state.cityCoordinates!!.second)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message,
                        )
                    }
                }
            } else {
                locationTracker.getCurrentLocation()?.let { location ->
                    when (val result =
                        repository.getWeatherData(location.latitude, location.longitude)) {
                        is Resource.Success -> {
                            state = state.copy(
                                weatherInfo = result.data,
                                isLoading = false,
                                error = null
                            )
                        }

                        is Resource.Error -> {
                            state = state.copy(
                                weatherInfo = null,
                                isLoading = false,
                                error = result.message,
                            )
                        }
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS"
                )
            }
        }
    }
}
