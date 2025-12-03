package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.profileScreen.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScreenNavigator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }

        }
    }
}

@Composable
fun ScreenNavigator(modifier : Modifier = Modifier) {
    var screen by remember { mutableStateOf("bacheca") }

    fun changeScreen(nextScreen:String) {
        screen = nextScreen
    }
    when (screen) {
        "bacheca" -> Bacheca(modifier, onChangeScreen = ::changeScreen)
        "profilo" -> ProfileScreen(modifier)
//        "creaPost" -> CreatePost(modifier)
        else -> Log.d("MyApp", "Schermata sbagliata!!!!")
    }


}

@Composable
fun Bacheca(modifier: Modifier = Modifier, onChangeScreen: (String) -> Unit) {
    Column() {
        ScreenPlaceholder(description = "bacheca", modifier = modifier.weight(1f), color = Color.Yellow)
        Row() {
            Button(onClick ={onChangeScreen("feed")}) {
                Text("vai a feed")
            }
            Button(onClick ={onChangeScreen("creaPost")}) {
                Text("Crea post")
            }
            Button(onClick = { onChangeScreen("profilo")}) {
                Text("vai a profilo")
            }
        }

    }

}

//@Composable
//fun Profile(modifier: Modifier = Modifier) {
//    ScreenPlaceholder(description = "profilo", modifier = modifier, color = Color.Green)
//}
//

@Composable
fun ScreenPlaceholder(description: String, modifier: Modifier = Modifier, color: Color = Color.Gray) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(description)
    }

}

@Composable
fun CreatePost(modifier: Modifier = Modifier) {
    ScreenPlaceholder(description = "crea post", modifier = modifier, color = Color.Cyan)

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name! ciaooo",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        Greeting("Android")

}