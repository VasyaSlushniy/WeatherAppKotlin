package screens

import Gson.City
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import navcontroller.NavController
import java.io.File


@Composable
fun ProfileScreen(
    navController: NavController
) {
    var data : String = readData()

    var nameText by remember { mutableStateOf(  data.substringBefore("/")) }
    var city by remember { mutableStateOf(data.substringAfter("/")) }

    val shape = RectangleShape
    Box(Modifier.padding(top = 50.dp, start = 100.dp, bottom = 100.dp,end = 50.dp).fillMaxSize().border(4.dp, Color.Blue, shape )) {

        Column (Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
            Text("Профіль", Modifier.padding(top = 5.dp))
            Text("Ім'я", Modifier.padding(top = 5.dp))
            TextField(
                value = nameText,
            onValueChange = {nameText = it}

            )
            Text("Місто", Modifier.padding(top = 5.dp))
            TextField(
                value = city,
                onValueChange = {city = it}

            )

            Button(onClick = {saveToFile(nameText, city)}){
                Text("Зберегти дані")
            }

        }
    }

}

fun saveToFile (name: String, city: String){
    File("src/main/kotlin/userData").writeText("$name/$city")

}

fun readData()
        = File("src/main/kotlin/userData").readText()