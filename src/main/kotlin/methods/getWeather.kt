package methods

import Gson.WeatherForecast

import com.google.gson.Gson
import setInternetConnection
import java.io.File
import java.net.URL

fun getWeather (CITY: String): WeatherForecast {
    val API = "29f7b967b48298af39b3b4e8c07242be"
    val response:String?
    var weatherForecast: WeatherForecast
    try{
        response = URL("https://api.openweathermap.org/data/2.5/forecast?q=$CITY&units=metric&appid=$API").
        readText(
            Charsets.UTF_8
        )
        val gson = Gson()
        weatherForecast = gson.fromJson(response, WeatherForecast::class.java)
        File("src/main/kotlin/Data/weatherData").writeText(response)

    }catch (e: Exception){
        val gson = Gson()
        weatherForecast = gson.fromJson(File("src/main/kotlin/Data/weatherData").readText(),WeatherForecast::class.java)
        setInternetConnection(false)
       // println(e.printStackTrace())
    }
    return weatherForecast
}

