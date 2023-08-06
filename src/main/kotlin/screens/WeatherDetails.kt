
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import methods.draw
import methods.init
import navcontroller.NavController
import java.time.LocalDate

private var detailDate :String? = ""
private var counter = 0
private var list = mutableListOf(0,0)
private var forecasts  = listOf(
   MutableStateFlow("0"),
   MutableStateFlow("1"),
   MutableStateFlow("2"),
   MutableStateFlow("3"),
   MutableStateFlow("4"),
   MutableStateFlow("5"),
   MutableStateFlow("6"),
   MutableStateFlow("7"),
   MutableStateFlow("8")
)

@Composable
fun WeatherDetails (
navController: NavController
) {
   var run = true
   var index = 0
   var bool : Boolean?
   Text("Детальна погода обраного дня", modifier = Modifier.padding(start = 275.dp, end = 250.dp).fillMaxWidth())
   if (detailDate== null || detailDate.equals("")) setDay(LocalDate.now().toString())
   while (run){
      bool = getWeatherForecast().list?.get(index)?.dtTxt?.substringBefore(" ")?.equals(getDay())

      if (bool!!){
         run = false
      } else index++
   }

   if (index >= 32 ) index = 31
   init(forecasts,1, index)


counter = index
   for (item in forecasts){
      draw(item, counter, 9 , list, navController)
      counter++
   }
   counter = 0

}

fun setDay (dateTx :String?){

   detailDate = dateTx
}

fun getDay():String? {
   return detailDate
}