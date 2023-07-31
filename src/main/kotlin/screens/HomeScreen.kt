package screens

import Cities
import Gson.WeatherForecast
import WeatherObj
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import kotlinx.coroutines.delay
import navcontroller.NavController
import kotlinx.coroutines.flow.MutableStateFlow


import java.net.URL


private var city : String = "kyiv, ua"
private var weatherForecast:WeatherForecast = WeatherForecast(null, null, null, null, null)
private var day1 = MutableStateFlow("text")
private var day2 = MutableStateFlow("text")
private var day3 = MutableStateFlow("text")
private var day4 = MutableStateFlow("")
private var day5 = MutableStateFlow("")
private var day6 = MutableStateFlow("")
private var counter = 0


@Composable
fun HomeScreen(
    navController: NavController
) {
     getWeather("kyiv, ua")
    init()
    DropdownDemo()

    day1.value = setText(0)
    day2.value = setText(8)
    day3.value = setText(16)
    draw(day1)
    draw(day2)
    draw(day3)
}


fun init (){
    counter = 0
    day1.value = setText(1)
    counter++
    day2.value = setText(8)
    counter++
    day3.value = setText(16)
    counter = 0


  //  val myText by day1.collectAsState()
}


@Composable
fun draw (day:MutableStateFlow<String>){
    val dayTx by day.collectAsState()
    Column(
        modifier = Modifier.padding(start = (100+counter*210).dp,).width(200.dp)
    ) {

        val shape = RectangleShape
        Text(text ="text = ${dayTx}",
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center),
            modifier = Modifier.padding(16.dp)
                .border(2.dp, MaterialTheme.colors.secondary, shape)
                .background(MaterialTheme.colors.primary, shape)
                .padding(16.dp)
        )
        counter++
//        Button(
//            onClick = {
//           //     navController.navigate(Screen.ProfileScreens.name)
//
//            }) {
//
//            Text("Refresh")
//        }

    }
    println("Number = " + weatherForecast.list?.size)

    println(counter)
}

fun getWeather (CITY: String){
    val API = "29f7b967b48298af39b3b4e8c07242be"
    val response:String?

    try{
       // response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").
        response = URL("https://api.openweathermap.org/data/2.5/forecast?q=$CITY&units=metric&appid=$API").
        readText(
            Charsets.UTF_8
        )
        val gson = Gson()
        weatherForecast = gson.fromJson(response,WeatherForecast::class.java)

    }catch (e: Exception){
        println(e.printStackTrace())
    }
}


fun setText (index: Int) : String{

    return "temp = ${weatherForecast.list?.get(index)?.main?.temp}\n" +
            "wind speed = ${weatherForecast.list?.get(index)?.wind?.speed}\n" +
            "humidity = ${weatherForecast.list?.get(index)?.main?.humidity} \n" +
            "pressure = ${weatherForecast.list?.get(index)?.main?.pressure} \n" +
            "clouds = ${weatherForecast.list?.get(index)?.clouds?.all}\n" +
            "Date = ${weatherForecast.list?.get(index)?.dtTxt}\n" +
            "time = ${weatherForecast.list?.get(index)?.dt}"
}


@Composable
fun DropdownDemo() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("kyiv", "vinnytsia", "sokyryany")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier.wrapContentSize(Alignment.TopCenter).fillMaxWidth(),
    contentAlignment = Alignment.TopCenter) {
        Text(items[selectedIndex],modifier = Modifier.width(200.dp).clickable(onClick = { expanded = true }).background(
            Color.Gray),
        textAlign = TextAlign.Center)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().background(
                Color.White)

        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    getWeather("${items.get(index)}, ua")
                        init()
                        expanded = false
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                }
            }
        }
    }
}

