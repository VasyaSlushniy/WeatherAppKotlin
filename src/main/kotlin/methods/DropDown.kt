package methods

import Gson.WeatherForecast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import getCity
import getWeather
import init
import kotlinx.coroutines.flow.MutableStateFlow
import setCity
import weatherForecast

@Composable
fun DropDown() {

    var expanded by remember { mutableStateOf(false) }
    val items = listOf("kyiv", "vinnytsia", "sokyryany")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }

    Box (modifier = Modifier.wrapContentSize(Alignment.TopCenter)
        .border(4.dp,Color.Blue,RoundedCornerShape(8.dp)),

        ) {
        Text(getCity(),modifier = Modifier.width(300.dp).clickable(onClick = { expanded = true }).background(
            Color.White),
            textAlign = TextAlign.Center)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(300.dp).background(
                Color.White)

        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedIndex = index
                    setCity(items[index])
                    weatherForecast = getWeather(getCity()+", ua")
//                    init(forecasts, step, 0)
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
