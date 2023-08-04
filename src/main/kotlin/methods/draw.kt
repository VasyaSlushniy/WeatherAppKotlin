package methods

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import getWeatherForecast
import kotlinx.coroutines.flow.MutableStateFlow
import navcontroller.NavController
import setDay



@Preview
@Composable
fun draw (day: MutableStateFlow<String>, index:Int, numberOfItems: Int, list: MutableList<Int>, navController: NavController){

    val dayTx by day.collectAsState()
    val shape = RoundedCornerShape(16.dp)

    Column(
        modifier = Modifier.padding(start = (100+ (list.get(0) %3)*210).dp, top = (list.get(1) * 180 + 25).dp).width(200.dp).
        border(2.dp, MaterialTheme.colors.secondary, shape)
            .background(MaterialTheme.colors.primary, shape)
            .padding(16.dp)
            .clickable {navigateToDetails(index, navController)
            },
    ) {

        Image(
            painter = painterResource(resourcePath = "Icons/${getWeatherForecast().list?.get(index)?.weather?.get(0)?.icon}.png"),
            contentDescription = "resources/Icons/01d.png",
            Modifier.size(50.dp).align(Alignment.CenterHorizontally),
        )
        Text(text =dayTx,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center),

            )
    }
    list[0] = list[0] +1
    if (list[0] %3==0) list[1]++
    if (list[0] ==numberOfItems) {
        list[0] = 0
        list[1] = 0
    }
}

fun navigateToDetails (index: Int,navController: NavController){
    setDay(getWeatherForecast().list?.get(index)?.dtTxt?.split(" ")?.get(0))
    navController.navigate(Screen.WeatherDetails.name)
}
