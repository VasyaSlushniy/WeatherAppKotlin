
import Gson.WeatherForecast
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import methods.getWeather
import navcontroller.NavController
import navcontroller.NavigationHost
import navcontroller.composable
import navcontroller.rememberNavController
import screens.*
import java.io.File
import kotlin.concurrent.thread

private var weatherForecast: WeatherForecast = WeatherForecast(null,null,null,null,null)

private var city:String = File(File("src/main/kotlin/Data/userData").readText().substringAfter("/")).toString()
private var internetConnection = true
@Composable
@Preview
fun App() {

    val screens = Screen.values().toList()
    val navController by rememberNavController(Screen.HomeScreen.name)
    val currentScreen by remember {
        navController.currentScreen
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.background(color = MaterialTheme.colors.background)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                NavigationRail(
                    modifier = Modifier.align(Alignment.CenterStart).fillMaxHeight().background(Color.Magenta)
                ) {
                    screens.forEach {
                        NavigationRailItem(
                            selected = currentScreen == it.name,
                            icon = {
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = it.label
                                )
                            },
                            label = {
                                Text(it.label)
                            },
                            alwaysShowLabel = false,
                            onClick = {
                                navController.navigate(it.name)
                            }
                        )
                    }
                }

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomNavigationHost(navController = navController)
                }
            }
        }
    }
    if (!internetConnection) dialogNoInternetConnection()
    thread {
        var c = 0
        while (true) {

        Thread.sleep(60000)
        setWeatherForecast(getWeather(getCity()+", ua"))
        updateWeatherHome()
        updateWeatherFive()
        updateWeatherThree()
            c++
            println("time $c")
    } }
}

fun main() = application {

    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition(Alignment.Center),
        isMinimized = false,
        width = 800.dp,
        height = 600.dp
    )

    weatherForecast = getWeather(getCity()+", ua")

    Window(onCloseRequest = ::exitApplication, visible = true, state = state) {
        App()

    }

}




enum class Screen(
    val label: String,
    val icon: ImageVector
) {
    HomeScreen(
        label = "Головна",
        icon = Icons.Filled.Home
    ),
    ThreeDaysScreen(
        label = "3 дні",
        icon = Icons.Filled.DateRange
    ),
    FiveDaysScreen(
        label = "5 днів",
        icon = Icons.Filled.DateRange
    ),
    ProfileScreens(
        label = "Профіль",
        icon = Icons.Filled.AccountBox
    ),
    WeatherDetails(
        label = "Детальніше",
        icon = Icons.Filled.Info
    )
}


@Composable
fun CustomNavigationHost(
    navController: NavController
) {
    NavigationHost(navController) {
        composable(Screen.HomeScreen.name) {
            HomeScreen(navController)
        }

        composable(Screen.ThreeDaysScreen.name) {
            ThreeDaysScreen(navController)
        }

        composable(Screen.FiveDaysScreen.name) {
            FiveDaysScreen(navController)
        }

        composable(Screen.ProfileScreens.name) {
            ProfileScreen(navController)
        }
        composable(Screen.WeatherDetails.name){
            WeatherDetails(navController)
        }

    }.build()



}

fun getCity ():String {
    return city
}

fun setCity (newCity:String){
    city = newCity
}


fun setInternetConnection (connection:Boolean){
    internetConnection = connection
}

fun getWeatherForecast ():WeatherForecast{
    return weatherForecast
}

fun setWeatherForecast (weather: WeatherForecast){
    weatherForecast = weather
}





