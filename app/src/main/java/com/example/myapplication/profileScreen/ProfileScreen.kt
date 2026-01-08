package com.example.myapplication.profileScreen

import android.util.Log.i
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.data.viewmodel.UserViewModel
import com.example.myapplication.navigation.Header
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen

import com.example.myapplication.components.LoadingScreen
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.post.PostDetail
import com.example.myapplication.post.PostGrid
import com.example.myapplication.post.PostList

@Composable
fun ProfileScreen(
    user: UserEntity?,
    nav: NavigationViewModel,
    userPosts: List<PostEntity?>,
    uvm: UserViewModel,
    modifier: Modifier = Modifier
) {
    var viewMode by remember { mutableStateOf(ViewMode.GRID) }

    if (user == null) {
        LoadingScreen()
        return
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Header fisso
        Header(nav, user)

        // Contenuto scrollabile
        when (viewMode) {
            ViewMode.LIST -> {
                PostList(
                    user = user,
                    nav = nav,
                    posts = userPosts,
                    viewMode = viewMode,
                    onViewModeChange = { viewMode = it },
                    onPostClick = { post ->
                        // Naviga al dettaglio del post
                        // nav.navigateToPostDetail(post.id)
                    }
                )
            }
            ViewMode.GRID -> {
                PostGrid(
                    user = user,
                    nav = nav,
                    posts = userPosts,
                    viewMode = viewMode,
                    onViewModeChange = { viewMode = it },
                    onPostClick = { post ->
                        // Naviga al dettaglio del post
                        // nav.navigateToPostDetail(post.id)
                    }
                )
            }
        }
    }
}



//
//@Composable
//fun ProfileScreen(user: UserEntity?, nav: NavigationViewModel, userPosts: List<PostEntity?>, uvm: UserViewModel, modifier: Modifier) {
//
////    val user = uvm.user
//
//    if (user == null) {
//        LoadingScreen()
//    }
//
//
//
//    Card(
////        elevation = CardDefaults.cardElevation(
////            defaultElevation = 6.dp
////        ),
////        colors = CardDefaults.cardColors(
////            containerColor = MaterialTheme.colorScheme.surfaceVariant
////        ),
//        modifier = Modifier
////            .statusBarsPadding()
//            .fillMaxWidth()
//
//    ) {
////        Spacer(modifier = Modifier.height(20.dp))
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFFFFFFFF)),
//            horizontalAlignment = Alignment.CenterHorizontally,
//
//            ) {
//            Header(nav, user)
//
//            if (user != null) {
//                i("profile", "User loaded: ${user.username}")
//                ProfileImage(user.profilePicture)
//                Spacer(modifier = Modifier.height(15.dp))
//                Text(
//                    "@" + user.username,
//                    textAlign = TextAlign.Center,
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(8.dp)
//                ) {
//                    if (user.dateOfBirth.isNotEmpty()) {
//                        Icon(
//                            imageVector = Icons.Filled.DateRange,
//                            contentDescription = "Data di nascita"
//                        )
//                        Text(user.dateOfBirth)
//                    } else {
//
//                        Icon(
//                            imageVector = Icons.Filled.DateRange,
//                            contentDescription = "Data di nascita",
//                            tint = Color.Gray
//                        )
//                        Text(
//                            " add your date of birth",
//                            color = Color.Gray
//                        )
//                    }
//                }
//                if (user.bio.isNotEmpty()) {
//                    Text(user.bio)
//                } else {
//                    Text(
//                        "add a biography",
//                        color = Color.Gray
//                    )
//                }
////                Text(user.bio, textAlign = TextAlign.Center)
//                Button(
//                    onClick = { nav.navigateTo(Screen.PROFILE_SETTINGS) },
//                    modifier = Modifier.padding(15.dp)
//                ) {
//                    Text("Edit Profile")
//                }
//
//                ProfileStats(
//                    user.postsCount,
//                    user.followersCount,
//                    user.followingCount
//                )
//
//            } else {
//                i("profile", "User is null")
//                LoadingScreen()
//            }
//
//
//            Column(
//                modifier = Modifier
//                    .background(Color(0xFFe5d3e5)),
//            ) {
//
//
//                PostGrid(userPosts) { post ->
//                }
//            }
//
//
//        }
//    }


//            Text(user.username, textAlign = TextAlign.Center)
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(8.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.DateRange,
//                    contentDescription = "Data di nascita"
//                )
//                Text(
//                    text = "12/04/2003",
//                )
//            }

//            Text("Finding beauty in the everyday ✨", textAlign = TextAlign.Center)

//            Button(
//                onClick = { nav.navigateTo(Screen.PROFILE_SETTINGS) },
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text("Edit Profile")
//            }
//
//            ProfileStats(
//                posts = 12,
//                followers = 340,
//                following = 198
//            )



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

//        var selectedIcon by remember { mutableStateOf("grid") }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(2.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .background(
//                        if (selectedIcon == "grid") Color(0xFF7d0885).copy(alpha = 0.2f)
//                        else Color.Transparent
//                    ),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                SelectableIcon(
//                    icon = Icons.Default.AccountBox,
//                    selected = selectedIcon == "grid",
//                    onClick = { selectedIcon = "grid" }
//                )
//            }
//
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .background(
//                        if (selectedIcon == "list") Color(0xFF7d0885).copy(alpha = 0.2f)
//                        else Color.Transparent
//                    ),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                SelectableIcon(
//                    icon = Icons.Default.List,
//                    selected = selectedIcon == "list",
//                    onClick = { selectedIcon = "list" }
//                )
//            }
//        }
//
//        val posts = listOf(
//            Post(R.drawable.pf, "Prima foto", null, LocalDateTime.now()),
//            Post(R.drawable.pf, "Tramonto", null, LocalDateTime.now()),
//            Post(R.drawable.pf, "Selfie", null, LocalDateTime.now())
//        )
//        Box( modifier = Modifier
//            .weight(1f)
//            .fillMaxWidth() )
//        {
//            when (selectedIcon) {
//                "grid" -> {
//                    Column(
//                        modifier = Modifier
//                            .background(Color(0xFFe5d3e5)),
//                    ) {
//                        PostGrid(posts) { post ->
//
//                        }
//                    }
//                }
////                    PostGrid(posts) { post ->
////                        PostItem(nav, post)
////                    }
////                }
//
//                "list" -> {
//                    Column(
//                        modifier = Modifier
//                            .background(Color(0xFFe5d3e5)),
//                    ) {
//                        PostList(nav, posts)
//                    }
//                }
//
//            }
//        }

//}

