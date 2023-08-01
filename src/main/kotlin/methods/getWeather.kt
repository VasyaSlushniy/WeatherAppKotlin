import Gson.WeatherForecast
import com.google.gson.Gson
import java.net.URL

fun getWeather (CITY: String): WeatherForecast {
    val API = "29f7b967b48298af39b3b4e8c07242be"
    val response:String?
    var weatherForecast = WeatherForecast(null, null, null, null, null)
    try{
        // response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").
        response = URL("https://api.openweathermap.org/data/2.5/forecast?q=$CITY&units=metric&appid=$API").
        readText(
            Charsets.UTF_8
        )
        val gson = Gson()
        weatherForecast = gson.fromJson(response, WeatherForecast::class.java)

    }catch (e: Exception){
        println(e.printStackTrace())
    }
    return weatherForecast
}
