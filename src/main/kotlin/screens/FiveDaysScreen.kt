package screens

import androidx.compose.runtime.Composable
import getCity
import getWeather

import init
import kotlinx.coroutines.flow.MutableStateFlow
import methods.draw
import navcontroller.NavController
import weatherForecast

private var forecasts  = listOf(
    MutableStateFlow("0"),
    MutableStateFlow("1"),
    MutableStateFlow("2"),
    MutableStateFlow("3"),
    MutableStateFlow("4"),
)
private var counter = 0
private var list = mutableListOf(0,0)

@Composable
fun FiveDaysScreen(
    navController: NavController
) {
    init(forecasts, 8, 0)

    for (item in forecasts){
        draw(item, counter, 5, list, navController)
        counter+=8
    }
    counter = 0

}

fun updateWeatherFive () {
    weatherForecast = getWeather(getCity()+", ua")
    var count = 0
    for (item in forecasts){
        item.value = "Температура: ${weatherForecast.list?.get(count)?.main?.temp}\n" +
                "Швидкість вітру: ${weatherForecast.list?.get(count)?.wind?.speed}\n" +
                "Вологість = ${weatherForecast.list?.get(count)?.main?.humidity} \n" +
                "Тиск = ${weatherForecast.list?.get(count)?.main?.pressure} \n" +
                "${weatherForecast.list?.get(count)?.dtTxt}\n"
        count += 8
    }
}

