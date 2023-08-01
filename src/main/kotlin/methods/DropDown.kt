package methods

import Gson.WeatherForecast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import getWeather
import init
import kotlinx.coroutines.flow.MutableStateFlow
import weatherForecast

@Composable
fun DropDown(forecasts:List<MutableStateFlow<String>>, step: Int) {

    var expanded by remember { mutableStateOf(false) }
    val items = listOf("kyiv", "vinnytsia", "sokyryany")
    val disabledValue = "B"
    val selectedIndex by remember { mutableStateOf(0) }

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
                    weatherForecast = getWeather("${items[index]}, ua")
                    init(forecasts, step)
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
