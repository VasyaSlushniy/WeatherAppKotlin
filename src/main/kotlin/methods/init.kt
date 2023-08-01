import Gson.WeatherForecast
import kotlinx.coroutines.flow.MutableStateFlow

fun init (forecast: List<MutableStateFlow<String>>, step :Int){
    var counter = 0
    for (item in forecast){
        item.value = setText(counter)
        counter += step
    }

}