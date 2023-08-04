package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import getCity
import methods.DropDown
import navcontroller.NavController
import java.io.File


@Composable
fun ProfileScreen(
    navController: NavController
) {
    val data : String = readData()

    var nameText by remember { mutableStateOf(  data.substringBefore("/")) }

    RectangleShape
    Box(Modifier.padding(top = 50.dp, start = 100.dp, bottom = 100.dp,end = 50.dp).fillMaxSize()) {

        Column (Modifier.fillMaxSize().padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
            Text("Профіль", Modifier.padding(top = 16.dp))
            Text("Ім'я", Modifier.padding(top = 16.dp, bottom = 4.dp))
            TextField(
                value = nameText,
            onValueChange = {nameText = it}

            )

            Text("Місто", Modifier.padding(top = 16.dp, bottom = 4.dp))

            DropDown()

            Button(onClick = {saveToFile(nameText)}, Modifier.padding(top = 24.dp)){
                Text("Зберегти дані")
            }

        }
    }

}

fun saveToFile (name: String){
    File("src/main/kotlin/Data/userData").writeText("$name/${getCity()}")

}

fun readData()
        = File("src/main/kotlin/Data/userData").readText()