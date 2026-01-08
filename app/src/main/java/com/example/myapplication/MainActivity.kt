package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.data.viewmodel.AppViewModel
import com.example.myapplication.data.viewmodel.AppViewModelFactory
import com.example.myapplication.navigation.NavBar
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.profileScreen.MainScreen
import com.example.myapplication.data.repository.SettingsRepository
import com.example.myapplication.data.viewmodel.UserViewModel
import com.example.myapplication.data.viewmodel.UserViewModelFactory
import com.example.myapplication.login.LoginScreen
import com.example.myapplication.navigation.Screen
import com.example.myapplication.components.LoadingScreen

data class BottomNavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private val Context.dataStore by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    private val nav: NavigationViewModel by viewModels()
    private lateinit var uvm: UserViewModel
    private lateinit var appViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        uvm = ViewModelProvider(
            this,
            UserViewModelFactory(applicationContext)
        )[UserViewModel::class.java]

        val settingsRepository = SettingsRepository(applicationContext.dataStore)
        appViewModel = ViewModelProvider(
            this,
            AppViewModelFactory(settingsRepository)
        )[AppViewModel::class.java]

        setContent {

            val firstLaunch by appViewModel.isFirstLaunch.collectAsState()

            when (firstLaunch) {
                true -> {
                    LoginScreen(onLoginCompleted = { username, profilePicBase64 ->
                        // Salva l’utente nel DB
                        uvm.saveUser(
                            UserEntity(
                                id = 0,
                                createdAt = System.currentTimeMillis().toString(),
                                username = username,
                                bio = "",
                                dateOfBirth = "",
                                profilePicture = profilePicBase64,
                                isYourFollower = false,
                                isYourFollowing = false,
                                followersCount = 0,
                                followingCount = 0,
                                postsCount = 0
                            )
                        )
                        // Segna che il primo avvio è finito
                        appViewModel.setFirstLaunchDone()
                        nav.navigateTo(Screen.FEED)
                    })
                }
                false -> { NavBar(nav, uvm, modifier = Modifier) }
                else -> {
                    // Loading iniziale
                    LoadingScreen()
                }

            }

//            val firstLaunch = appViewModel.isFirstLaunch
//
//            LaunchedEffect(firstLaunch) {
//                if (firstLaunch == true) {
//                    appViewModel.setFirstLaunchDone()
//                    nav.navigateTo(Screen.FEED)
//                }
//            }
//
//            MyApplicationTheme {
//                NavBar(nav, uvm, modifier = Modifier)
//            }
                    }
                }
            }

            @Composable
            fun Greeting(
                nav: NavigationViewModel,
                uvm: UserViewModel,
                modifier: Modifier = Modifier
            ) {
//
//                LaunchedEffect(Unit) {
//                    uvm.storeUser(UsersExample().users)
//                }

                MainScreen(nav, uvm, modifier = modifier)
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