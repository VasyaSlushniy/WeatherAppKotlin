package screens


import androidx.compose.runtime.Composable

import init
import kotlinx.coroutines.flow.MutableStateFlow
import methods.draw
import navcontroller.NavController

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
fun FiveDaysScreen(
    navController: NavController
) {
    init(forecasts, 8)

    for (item in forecasts){
        draw(item, counter, 5, list)
        counter++
    }
    counter = 0
}
