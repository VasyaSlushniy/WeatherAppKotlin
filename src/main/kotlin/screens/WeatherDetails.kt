
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.MutableStateFlow
import methods.draw
import navcontroller.NavController
import java.time.LocalDate



private var date :String? = ""
private var counter = 0
private var list = mutableListOf(0,0)
private var forecasts  = listOf(
   MutableStateFlow("0"),
   MutableStateFlow("1"),
   MutableStateFlow("2"),
   MutableStateFlow("3"),
   MutableStateFlow("4"),
   MutableStateFlow("5"),
   MutableStateFlow("6") ,
   MutableStateFlow("7"),
   MutableStateFlow("8")
)

@Composable
fun WeatherDetails (
navController: NavController, date: LocalDate
) {
   var run = true
   var index = 0
   var bool : Boolean?
   while (run){
      println(date)
      println(weatherForecast.list?.get(index)?.dtTxt?.substringBefore(" "))
      bool = weatherForecast.list?.get(index)?.dtTxt?.substringBefore(" ")?.equals(getDay())
      println("$index")
      if (bool!!){
         run = false
      } else index++
   }

   println(index)
   init(forecasts,1, index)


counter = index
   for (item in forecasts){
      draw(item, counter, 9 , list, navController)
      counter++
   }
   counter = 0

}




fun setDay (dateTx :String?){
   date = dateTx
}

fun getDay():String? {
   return date
}