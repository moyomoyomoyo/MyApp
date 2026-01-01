package com.example.myapplication.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.navigation.Header
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen
import com.example.myapplication.post.Post
import com.example.myapplication.post.PostGrid

import com.example.myapplication.post.PostItem
import com.example.myapplication.post.PostList

import java.time.LocalDateTime


//data class User(
//    val id: String,
//    val createdAt: Int,
//    var username: String,
//    var bio: String,
//    var dateOfBirth: String,
//    val profilePicture: String,
//    val isYourFollower: Int,
//    val isYourFollowing: Int,
//    val followersCount: Int,
//    val followingCount: Int,
//    val postsCount: Int
//)

@Composable
fun ProfileScreen(nav: NavigationViewModel, modifier: Modifier) {
    Card(
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 6.dp
//        ),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surfaceVariant
//        ),
        modifier = Modifier
//            .statusBarsPadding()
            .fillMaxWidth()

    ) {
//        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF)),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Header(nav)
            ProfileImage(imageRes = R.drawable.pf)

            Text("@moyo", textAlign = TextAlign.Center)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Data di nascita"
                )
                Text(
                    text = "12/04/2003",
                )
            }

            Text("Finding beauty in the everyday ✨", textAlign = TextAlign.Center)

            Button(
                onClick = { nav.navigateTo(Screen.PROFILE_SETTINGS) },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Edit Profile")
            }

            ProfileStats(
                posts = 12,
                followers = 340,
                following = 198
            )

}


//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(2.dp),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                SelectableIcon(
//                    icon = Icons.Default.AccountBox,
//                    selected = selectedIcon == "grid",
//                    onClick = { selectedIcon = "grid" }
//                )
//
//                SelectableIcon(
//                    icon = Icons.Default.List,
//                    selected = selectedIcon == "list",
//                    onClick = {
//                        selectedIcon = "list"
//                    }
//                )
//            }


//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 0.dp),
//                horizontalArrangement = Arrangement.SpaceEvenly,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                IconButton(
//                    onClick = {  },
//                    modifier = Modifier
//                        .padding(0.dp)
//                        .background(Color(0xFFE0E0E0)),
//                ) {
//                    Icon(
//                        imageVector = Icons.Outlined.AccountBox,
//                        contentDescription = "Close fullscreen",
//                        tint = Color.Black,
//                        modifier = Modifier.size(30.dp)
//                    )
//                }
//
//                IconButton(
//                    onClick = {  },
//                    modifier = Modifier
//                        .padding(0.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Outlined.List,
//                        contentDescription = "Close fullscreen",
//                        tint = Color.Black,
//                        modifier = Modifier.size(30.dp)
//                    )
//                }
//            }


//        Column(
//            modifier = Modifier
//                .background(Color(0xFFe5d3e5)),
//        ) {
//            val posts = listOf(
//                Post(R.drawable.pf, "Prima foto", null, LocalDateTime.now()),
//                Post(R.drawable.pf, "Tramonto", null, LocalDateTime.now()),
//                Post(R.drawable.pf, "Selfie", null, LocalDateTime.now())
//            )
//
//            PostGrid(posts) { post ->
//
//            }
//        }
        var selectedIcon by remember { mutableStateOf("grid") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        if (selectedIcon == "grid") Color(0xFF7d0885).copy(alpha = 0.2f)
                        else Color.Transparent
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SelectableIcon(
                    icon = Icons.Default.AccountBox,
                    selected = selectedIcon == "grid",
                    onClick = { selectedIcon = "grid" }
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        if (selectedIcon == "list") Color(0xFF7d0885).copy(alpha = 0.2f)
                        else Color.Transparent
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SelectableIcon(
                    icon = Icons.Default.List,
                    selected = selectedIcon == "list",
                    onClick = { selectedIcon = "list" }
                )
            }
        }

        val posts = listOf(
            Post(R.drawable.pf, "Prima foto", null, LocalDateTime.now()),
            Post(R.drawable.pf, "Tramonto", null, LocalDateTime.now()),
            Post(R.drawable.pf, "Selfie", null, LocalDateTime.now())
        )
        Box( modifier = Modifier
            .weight(1f)
            .fillMaxWidth() )
        {
            when (selectedIcon) {
                "grid" -> {
                    Column(
                        modifier = Modifier
                            .background(Color(0xFFe5d3e5)),
                    ) {
                        PostGrid(posts) { post ->

                        }
                    }
                }
//                    PostGrid(posts) { post ->
//                        PostItem(nav, post)
//                    }
//                }

                "list" -> {
                    Column(
                        modifier = Modifier
                            .background(Color(0xFFe5d3e5)),
                    ) {
                        PostList(nav, posts)
                    }
                }

            }
        }
    }
}