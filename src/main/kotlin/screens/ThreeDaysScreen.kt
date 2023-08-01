package screens

import Gson.WeatherForecast
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import getWeather
import init
import kotlinx.coroutines.flow.MutableStateFlow
import methods.draw
import navcontroller.NavController
import java.net.URL


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
fun ThreeDaysScreen(
    navController: NavController
) {
    init(forecasts, 8)

    for (item in forecasts){
        draw(item, counter, 3,list )
        counter++
    }
    counter = 0
}



