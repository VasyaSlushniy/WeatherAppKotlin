package screens

import Gson.WeatherForecast
import androidx.compose.runtime.*
import getCity
import getWeather
import init
import kotlinx.coroutines.flow.MutableStateFlow
import methods.DropDown
import methods.draw
import navcontroller.NavController
import navcontroller.rememberNavController
import setText


var weatherForecast:WeatherForecast = WeatherForecast(null, null, null, null, null)
private var forecasts  = listOf(MutableStateFlow("0"),
    MutableStateFlow("1"),
    MutableStateFlow("2"),
    MutableStateFlow("3"),
    MutableStateFlow("4"),
    MutableStateFlow("5"),
    MutableStateFlow("6") ,
    MutableStateFlow("7"),
    MutableStateFlow("8"))

private var counter = 0
private var list = mutableListOf(0,0)
@Composable
fun HomeScreen(
    navController: NavController
) {
     weatherForecast = getWeather(getCity()+", ua")
    init(forecasts,1, 0)


    for (item in forecasts){
        draw(item, counter, 9 , list, navController)
        counter++
    }
    counter = 0
    DropDown(forecasts,1)

}

fun updateWeatherHome () {
   weatherForecast = getWeather(getCity()+", ua")
    var count = 0
    for (item in forecasts){
        item.value = "Температура: ${weatherForecast.list?.get(count)?.main?.temp}\n" +
                "Швидкість вітру: ${weatherForecast.list?.get(count)?.wind?.speed}\n" +
                "Вологість = ${weatherForecast.list?.get(count)?.main?.humidity} \n" +
                "Тиск = ${weatherForecast.list?.get(count)?.main?.pressure} \n" +
                "${weatherForecast.list?.get(count)?.dtTxt}\n"
        count += 1
    }
}


