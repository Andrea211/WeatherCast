package com.neonfunapps.weathercast.domain.weather

import androidx.annotation.DrawableRes
import com.neonfunapps.weathercast.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    data object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.ic_sun
    )

    data object MainlyClear : WeatherType(
        weatherDesc = "Mainly clear",
        iconRes = R.drawable.ic_sun
    )

    data object PartlyCloudy : WeatherType(
        weatherDesc = "Partly cloudy",
        iconRes = R.drawable.ic_sun_clouds
    )

    data object Overcast : WeatherType(
        weatherDesc = "Overcast",
        iconRes = R.drawable.ic_clouds
    )

    data object Foggy : WeatherType(
        weatherDesc = "Foggy",
        iconRes = R.drawable.ic_fog
    )

    data object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing rime fog",
        iconRes = R.drawable.ic_fog
    )

    data object LightDrizzle : WeatherType(
        weatherDesc = "Light drizzle",
        iconRes = R.drawable.ic_rain
    )

    data object ModerateDrizzle : WeatherType(
        weatherDesc = "Moderate drizzle",
        iconRes = R.drawable.ic_rain
    )

    data object DenseDrizzle : WeatherType(
        weatherDesc = "Dense drizzle",
        iconRes = R.drawable.ic_rain
    )

    data object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight freezing drizzle",
        iconRes = R.drawable.ic_rain
    )

    data object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Dense freezing drizzle",
        iconRes = R.drawable.ic_rain
    )

    data object SlightRain : WeatherType(
        weatherDesc = "Slight rain",
        iconRes = R.drawable.ic_rain
    )

    data object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        iconRes = R.drawable.ic_rain
    )

    data object HeavyRain : WeatherType(
        weatherDesc = "Heavy rain",
        iconRes = R.drawable.ic_rain
    )

    data object HeavyFreezingRain : WeatherType(
        weatherDesc = "Heavy freezing rain",
        iconRes = R.drawable.ic_rain
    )

    data object SlightSnowFall : WeatherType(
        weatherDesc = "Slight snow fall",
        iconRes = R.drawable.ic_snow
    )

    data object ModerateSnowFall : WeatherType(
        weatherDesc = "Moderate snow fall",
        iconRes = R.drawable.ic_snow
    )

    data object HeavySnowFall : WeatherType(
        weatherDesc = "Heavy snow fall",
        iconRes = R.drawable.ic_snow
    )

    data object SnowGrains : WeatherType(
        weatherDesc = "Snow grains",
        iconRes = R.drawable.ic_snow
    )

    data object SlightRainShowers : WeatherType(
        weatherDesc = "Slight rain showers",
        iconRes = R.drawable.ic_rain
    )

    data object ModerateRainShowers : WeatherType(
        weatherDesc = "Moderate rain showers",
        iconRes = R.drawable.ic_rain
    )

    data object ViolentRainShowers : WeatherType(
        weatherDesc = "Violent rain showers",
        iconRes = R.drawable.ic_rain
    )

    data object SlightSnowShowers : WeatherType(
        weatherDesc = "Light snow showers",
        iconRes = R.drawable.ic_snow
    )

    data object HeavySnowShowers : WeatherType(
        weatherDesc = "Heavy snow showers",
        iconRes = R.drawable.ic_snow
    )

    data object ModerateThunderstorm : WeatherType(
        weatherDesc = "Moderate thunderstorm",
        iconRes = R.drawable.ic_storm
    )

    data object SlightHailThunderstorm : WeatherType(
        weatherDesc = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_storm_rain
    )

    data object HeavyHailThunderstorm : WeatherType(
        weatherDesc = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_storm_rain
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when (code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}