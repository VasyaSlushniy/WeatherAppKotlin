package screens

import Gson.WeatherForecast
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow


import java.net.URL


private var weatherForecast:WeatherForecast = WeatherForecast(null, null, null, null, null)
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
private var countline = 0
private var counterDraw = 0


@Composable
fun HomeScreen(
    //navController: NavController
) {
     getWeather("kyiv, ua")
    init()
    DropdownDemo()

    for (item in forecasts){
        draw(item, counter)
        counter++
    }
    counter = 0



}


fun init (){
    counter = 0
    for (item in forecasts){
        item.value = setText(counter)
        counter++
    }
    counter = 0

}

@Preview
@Composable
fun draw (day:MutableStateFlow<String>, index:Int){

    val dayTx by day.collectAsState()
    val shape = RoundedCornerShape(16.dp)
    Column(
        modifier = Modifier.padding(start = (100+ (counterDraw%3)*210).dp, top = (countline* 180 + 25).dp).width(200.dp).
            border(2.dp, MaterialTheme.colors.secondary, shape)
            .background(MaterialTheme.colors.primary, shape)
            .padding(16.dp)
    ) {

        Image(
            painter = painterResource(resourcePath = "Icons/${weatherForecast.list?.get(index)?.weather?.get(0)?.icon}.png"),
            contentDescription = "resources/Icons/01d.png",
            Modifier.size(50.dp).align(Alignment.CenterHorizontally),
        )
        Text(text =dayTx,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center),

        )
//        Button(
//            onClick = {
//           //     navController.navigate(Screen.ProfileScreens.name)
//
//            }) {
//
//            Text("Refresh")
//        }

    }
    counterDraw++
    if (counterDraw%3==0) countline++
    if (counterDraw==9) {
        counterDraw= 0
    countline = 0}
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

    return "Температура: ${weatherForecast.list?.get(index)?.main?.temp}\n" +
            "Швидкість вітру: ${weatherForecast.list?.get(index)?.wind?.speed}\n" +
            "Вологість = ${weatherForecast.list?.get(index)?.main?.humidity} \n" +
            "Тиск = ${weatherForecast.list?.get(index)?.main?.pressure} \n" +
            "${weatherForecast.list?.get(index)?.dtTxt}\n"
           // "${weatherForecast.list?.get(index)?.dt} \n "
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

