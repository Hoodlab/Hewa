package com.example.hewa.ui.results

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hewa.domain.models.CurrentWeather
import com.example.hewa.domain.repository.State
import com.example.hewa.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {
    var state by mutableStateOf(ResultState())


    fun searchWeatherInfo(city: String) {
        viewModelScope.launch {
            state = state.copy(weather = repository.getWeatherData(city = city))
        }
    }

}

data class ResultState(
    val weather: State<CurrentWeather> = State.Loading(),
)