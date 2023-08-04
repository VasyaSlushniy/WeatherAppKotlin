package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun dialogNoInternetConnection () {
    var showDialog by remember { mutableStateOf(true) }
    val shape = RoundedCornerShape(20.dp)

    if (showDialog) {

            AlertDialog(
                modifier = Modifier.width(400.dp).background(Color.Cyan,shape),
                backgroundColor = Color.LightGray,
                onDismissRequest = { showDialog = false },
                title = { Text("Відсутнє з'єднання з мережею інтернет.") },
                text = { Text("Неможливо завантажити нові дані, тому було відображено крайні збереженні дані.") },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Ок".uppercase())
                    }
                },

            )
    }
}