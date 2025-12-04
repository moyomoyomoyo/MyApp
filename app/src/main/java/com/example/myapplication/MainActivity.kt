package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.createPost.CreatePostScreen
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen
import com.example.myapplication.profileScreen.FeedScreen
import com.example.myapplication.profileScreen.LoginScreen
import com.example.myapplication.profileScreen.MainScreen
import com.example.myapplication.profileScreen.ProfileScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

data class BottomNavItem(val label: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector)
class MainActivity : ComponentActivity() {
    private val nav: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                var isLoggedIn by remember { mutableStateOf(false) } // stato login
                var selectedItemIndex by remember { mutableStateOf(1) } // navbar

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (!isLoggedIn) {
                        // 🔹 Schermata di login senza navbar
                        LoginScreen { name ->
                            // dopo login
                            isLoggedIn = true
                        }
                    } else {
                        // 🔹 Schermate principali con navbar
                        val items = listOf(
                            BottomNavItem("AddPhoto", Icons.Filled.AddCircle, Icons.Outlined.Add),
                            BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
                            BottomNavItem("Profile", Icons.Filled.Person, Icons.Outlined.Person)
                        )

                        Scaffold(
                            bottomBar = {
                                NavigationBar {
                                    items.forEachIndexed { index, item ->
                                        NavigationBarItem(
                                            selected = selectedItemIndex == index,
                                            onClick = { selectedItemIndex = index },
                                            label = { Text(item.label) },
                                            icon = {
                                                Icon(
                                                    imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                                                    contentDescription = item.label
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        ) { innerPadding ->
                            when (selectedItemIndex) {
                                0 -> nav.navigateTo(Screen.CREATE_POST)
                                1 -> nav.navigateTo(Screen.FEED)
                                2 -> nav.navigateTo(Screen.PROFILE)
                            }
                            Greeting(nav, modifier = Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(nav: NavigationViewModel, modifier: Modifier = Modifier) {
    MainScreen(nav = nav, modifier = modifier)
}


//
//@Composable
//fun ScreenNavigator(modifier : Modifier = Modifier) {
//    var screen by remember { mutableStateOf("bacheca") }
//
//    fun changeScreen(nextScreen:String) {
//        screen = nextScreen
//    }
//    when (screen) {
//        "bacheca" -> Bacheca(modifier, onChangeScreen = ::changeScreen)
//        "profilo" -> ProfileScreen(modifier)
////        "creaPost" -> CreatePost(modifier)
//        else -> Log.d("MyApp", "Schermata sbagliata!!!!")
//    }
//
//
//}

//@Composable
//fun Bacheca(modifier: Modifier = Modifier, onChangeScreen: (String) -> Unit) {
//    Column() {
//        ScreenPlaceholder(description = "bacheca", modifier = modifier.weight(1f), color = Color.Yellow)
//        Row() {
//            Button(onClick ={onChangeScreen("feed")}) {
//                Text("vai a feed")
//            }
//            Button(onClick ={onChangeScreen("creaPost")}) {
//                Text("Crea post")
//            }
//            Button(onClick = { onChangeScreen("profilo")}) {
//                Text("vai a profilo")
//            }
//        }
//
//    }
//
//}

//@Composable
//fun Profile(modifier: Modifier = Modifier) {
//    ScreenPlaceholder(description = "profilo", modifier = modifier, color = Color.Green)
//}
//

//@Composable
//fun ScreenPlaceholder(description: String, modifier: Modifier = Modifier, color: Color = Color.Gray) {
//    Box(
//        modifier = modifier
//            .fillMaxSize()
//            .background(color),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(description)
//    }
//
//}
//
//@Composable
//fun CreatePost(modifier: Modifier = Modifier) {
//    ScreenPlaceholder(description = "crea post", modifier = modifier, color = Color.Cyan)
//
//}
//



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Greeting("Android")
//
//}