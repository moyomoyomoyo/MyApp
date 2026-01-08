package com.example.myapplication.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.BottomNavItem
import com.example.myapplication.Greeting
import com.example.myapplication.data.viewmodel.UserViewModel

@Composable
fun NavBar(nav: NavigationViewModel, uvm: UserViewModel, modifier: Modifier) {

    val items = listOf(
        BottomNavItem("Create", Icons.Filled.AddBox, Icons.Outlined.AddBox),
        BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem("Profile", Icons.Filled.Person, Icons.Outlined.Person)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->

                    val screenForIndex = when (index) {
                        0 -> Screen.CREATE_POST
                        1 -> Screen.FEED
                        2 -> Screen.PROFILE
                        else -> Screen.FEED
                    }

                    NavigationBarItem(
                        selected = nav.currentScreen == screenForIndex,
                        onClick = { nav.navigateTo(screenForIndex) },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFba87ba),
                            indicatorColor = Color(0xFFe5d3e5),
                        ),
                        icon = {
                            Icon(
                                imageVector = if (nav.currentScreen == screenForIndex)
                                    item.selectedIcon
                                else
                                    item.unselectedIcon,
                                contentDescription = item.label
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Greeting(nav, uvm, modifier = Modifier.padding(innerPadding))
        }
    }
}

//@Composable
//fun NavBar(nav: NavigationViewModel, uvm: UserViewModel, modifier: Modifier) {
//    var selectedItemIndex by remember { mutableStateOf(1) }
//
//    val items = listOf(
//        BottomNavItem("Create", Icons.Filled.AddCircle, Icons.Outlined.Add),
//        BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
//        BottomNavItem("Profile", Icons.Filled.Person, Icons.Outlined.Person)
//    )
//
//    Scaffold(
//        bottomBar = {
//            NavigationBar {
//                items.forEachIndexed { index, item ->
//                    NavigationBarItem(
//                        selected = selectedItemIndex == index,
//                        onClick = { selectedItemIndex = index },
//                        label = { Text(item.label) },
//                        colors = NavigationBarItemDefaults.colors(
//                            selectedIconColor = Color(0xFFba87ba),
//                            indicatorColor = Color(0xFFe5d3e5),
//                        ),
//                        icon = {
//                            Icon(
//                                imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
//                                contentDescription = item.label
//                            )
//                        }
//                    )
//                }
//            }
//        }
//    ) { innerPadding ->
//        Column(modifier = Modifier.padding(innerPadding)) {
//            when (selectedItemIndex) {
//                0 -> nav.navigateTo(Screen.CREATE_POST)
//                1 -> nav.navigateTo(Screen.FEED)
//                2 -> nav.navigateTo(Screen.PROFILE)
//            }
//            Greeting(nav, uvm, modifier = Modifier.padding(innerPadding))
//        }
//    }
//}