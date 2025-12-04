package com.example.myapplication.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.BottomNavItem
import com.example.myapplication.Greeting

import com.example.myapplication.ui.theme.MyApplicationTheme


@Composable
fun NavBar(nav: NavigationViewModel,modifier: Modifier) {
//    val items = listOf(
//        BottomNavItem("AddPhoto", Icons.Filled.AddCircle, Icons.Outlined.AddCircle),
//        BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
//        BottomNavItem("Profile", Icons.Filled.Person, Icons.Outlined.Person)
//    )
//
//    var selectedItemIndex by remember { mutableStateOf(0) }
//
//    MyApplicationTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            Scaffold(
//                bottomBar = {
//                    NavigationBar {
//                        items.forEachIndexed { index, item ->
//                            NavigationBarItem(
//                                selected = selectedItemIndex == index,
//                                onClick = { selectedItemIndex = index },
//                                label = { Text(item.label) },
//                                icon = {
//                                    Icon(
//                                        imageVector = if (selectedItemIndex == index)
//                                            item.selectedIcon
//                                        else item.unselectedIcon,
//                                        contentDescription = item.label
//                                    )
//                                }
//                            )
//                        }
//                    }
//                }
//            ) { innerPadding ->
//                when (selectedItemIndex) {
//                    0 -> {
//                        nav.navigateTo(Screen.PROFILE)
//                        Greeting(nav, modifier = Modifier.padding(innerPadding))
//                    }
//                    1 -> {
//                        nav.navigateTo(Screen.PROFILE)
//                        Greeting(nav, modifier = Modifier.padding(innerPadding))
//                    }
//                    2 -> {
//                        nav.navigateTo(Screen.PROFILE)
//                        Greeting(nav, modifier = Modifier.padding(innerPadding))
//                    }
//                }
//            }
//        }
//    }
}