package screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import getWeatherForecast
import methods.init
import kotlinx.coroutines.flow.MutableStateFlow
import methods.draw
import navcontroller.NavController


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
    Text("Погода на 5 днів", modifier = Modifier.padding(start = 325.dp, end = 300.dp).fillMaxWidth())

    init(forecasts, 8, 0)

    for (item in forecasts){
        draw(item, counter, 5, list, navController)
        counter+=8
    }
    counter = 0

}

fun updateWeatherFive () {
    weatherForecast = getWeatherForecast()
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

