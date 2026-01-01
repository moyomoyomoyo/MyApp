package com.example.myapplication.post

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.navigation.MapDialog
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen
import com.example.myapplication.profileScreen.ProfileImage


@Composable
fun PostItem(nav: NavigationViewModel, post: Post) {
    val isFriend = true
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .then(
                if (isFriend) {
                    Modifier.border(
                        width = 3.dp,
                        color = Color(0xFF7d0885),
                        shape = RoundedCornerShape(12.dp)
                    )
                } else {
                    Modifier
                }
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
//            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
//                IconButton(onClick = onClose) {
//                    Icon(
//                        imageVector = Icons.Outlined.Close,
//                        contentDescription = "Close",
//                        tint = Color.Black,
//                        modifier = Modifier.size(30.dp)
//                    )
//                }
//            }

            Column(
                modifier = Modifier
                    .background(Color(0xFFF0F0F0))
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    ProfileImage(imageRes = R.drawable.pf, 40.dp, true)
                    Text(
                        text = "moyo",
                        fontSize = 21.sp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFb6a7b5).copy(alpha = 0.1f))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                ) {
//                    post.location?.let {
//                        Text(
//                            text = "📍 $it",
//                            fontSize = 15.sp,
//                            modifier = Modifier.clickable {
//                                nav.navigateTo(Screen.MAP_SCREEN)
//                            }
//                        )
//                    }
                    MapDialog(post, onDismiss = {
                        nav.navigateTo(Screen.FEED)
                    })

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = post.createdAt.hour.toString() + ":" + post.createdAt.minute + " " +
                                post.createdAt.dayOfMonth.toString() + "/" +
                                post.createdAt.monthValue.toString() + "/" +
                                post.createdAt.year.toString(),
                        fontSize = 14.sp,
                    )
                }
            }

//            Image(
//                painter = painterResource(id = post.imageRes),
//                contentDescription = post.description,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(450.dp)
//            )
//
            PostFullscreenImage(post.imageRes)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF0F0F0))
                    .padding(12.dp)
            ) {
                Text(
                    text = post.description,
                    fontSize = 16.sp,
                )
            }
        }
    }

}
