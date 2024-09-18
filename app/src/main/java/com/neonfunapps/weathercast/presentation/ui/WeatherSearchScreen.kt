package com.neonfunapps.weathercast.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.neonfunapps.weathercast.presentation.WeatherViewModel

@Composable
fun SearchScreen(viewModel: WeatherViewModel, navController: NavHostController) {
    var query by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    fun isCityNameValid(text: String): Boolean {
        return text.matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ ]+$".toRegex())
    }

    fun performSearch() {
        if (query.isNotEmpty() && !isError) {
            val cityName = query.lowercase().replaceFirstChar { it.uppercase() }
            navController.navigate("details/$cityName")
            viewModel.loadWeatherInfo()
            focusManager.clearFocus()
        }
    }

    LaunchedEffect(query) {
        if (query.isNotEmpty() && !isError) {
            viewModel.getListOfSuggestedCities(query)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 32.dp, 16.dp, 16.dp)
            .clickable(onClick = { focusManager.clearFocus() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
    ) {
        SearchTextField(query = query, isError = isError, onQueryChanged = { newQuery ->
            query = newQuery
            isError = newQuery.isNotEmpty() && !isCityNameValid(newQuery)
        }, onSearchClicked = { performSearch() })

        if (viewModel.state.listOfSuggestedCities?.isNotEmpty() == true && query.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(viewModel.state.listOfSuggestedCities ?: emptyList()) { cityTriple ->
                    CitySuggestionItem(city = cityTriple.first, country = cityTriple.second) {
                        query = cityTriple.first
                        performSearch()
                    }
                }
            }
        }
    }
}

@Composable
fun CitySuggestionItem(city: String, country: String, onCitySelected: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCitySelected() }
            .padding(8.dp)
    ) {
        Text(text = "$city, $country")
    }
}

@Composable
fun SearchTextField(
    query: String, isError: Boolean, onQueryChanged: (String) -> Unit, onSearchClicked: () -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        label = { Text("Search") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Invalid input: only letters and Polish characters allowed.",
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = {
                if (!isError) onSearchClicked()
            }) {
                Icon(
                    imageVector = if (isError) Icons.Default.Warning else Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        },
        keyboardActions = KeyboardActions(onDone = {
            if (!isError) onSearchClicked()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        )
    )
}