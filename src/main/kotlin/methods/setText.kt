package methods

import getWeatherForecast

fun setText (index: Int) : String{
 val weatherForecast = getWeatherForecast()
    return "Температура: ${weatherForecast.list?.get(index)?.main?.temp}\n" +
            "Швидкість вітру: ${weatherForecast.list?.get(index)?.wind?.speed}\n" +
            "Вологість: ${weatherForecast.list?.get(index)?.main?.humidity} \n" +
            "Тиск: ${weatherForecast.list?.get(index)?.main?.pressure} \n" +
            "${weatherForecast.list?.get(index)?.dtTxt}\n"
    // "${weatherForecast.list?.get(index)?.dt} \n "
}
