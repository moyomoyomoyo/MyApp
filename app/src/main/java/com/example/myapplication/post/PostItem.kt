package com.example.myapplication.post

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.navigation.MapDialog
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen
import com.example.myapplication.profileScreen.ProfileImage
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun PostItem(
    post: PostEntity,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Immagine del post se presente
//            post.contentPicture.let { imageUrl ->
//                Image(
//                    bitmap = imagePicture(imageUrl).asImageBitmap(),
//                    contentDescription = "Post image",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(250.dp)
//                        .clip(RoundedCornerShape(8.dp)),
//                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.height(12.dp))
//            }

            // Caption/contenuto del post
            Text(
                text = post.contentText ?: "",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Info post (likes, commenti, data)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
//                    Icon(
//                        imageVector = Icons.Filled.Favorite,
//                        contentDescription = "Likes",
//                        modifier = Modifier.size(16.dp),
//                        tint = Color.Gray
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text(
//                        text = "${post.likesCount}",
//                        style = MaterialTheme.typography.bodySmall,
//                        color = Color.Gray
//                    )
//
//                    Spacer(modifier = Modifier.width(16.dp))
//
//                    Icon(
//                        imageVector = Icons.Filled.Comment,
//                        contentDescription = "Comments",
//                        modifier = Modifier.size(16.dp),
//                        tint = Color.Gray
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text(
//                        text = "${post.commentsCount}",
//                        style = MaterialTheme.typography.bodySmall,
//                        color = Color.Gray
//                    )
//                }

                    Text(
                        text = post.createdAt,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}


//fun PostItem(nav: NavigationViewModel, post: PostEntity?, user: UserEntity?) {
//
//    if (post == null) return
//    if (user == null) return
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 10.dp, horizontal = 5.dp)
//            .then(
//                if (user.isYourFollowing) {
//                    Modifier.border(
//                        width = 3.dp,
//                        color = Color(0xFF7d0885),
//                        shape = RoundedCornerShape(12.dp)
//                    )
//                } else {
//                    Modifier
//                }
//            ),
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
//    ) {
//        Column {
////            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
////                IconButton(onClick = onClose) {
////                    Icon(
////                        imageVector = Icons.Outlined.Close,
////                        contentDescription = "Close",
////                        tint = Color.Black,
////                        modifier = Modifier.size(30.dp)
////                    )
////                }
////            }
//
//            Column(
//                modifier = Modifier
//                    .background(Color(0xFFF0F0F0))
//            ) {
//                Row(
//                    modifier = Modifier
//                        .padding(10.dp)
//                ) {
//                    ProfileImage(
//                        base64Image = user.profilePicture,
//                        size = 50.dp,
//                        isFriend = user.isYourFollowing
//                    )
//                    Text(
//                        text = user.username,
//                        fontSize = 21.sp,
//                        modifier = Modifier
//                            .padding(start = 8.dp)
//                            .align(alignment = Alignment.CenterVertically),
//                    )
//                }
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color(0xFFb6a7b5).copy(alpha = 0.05f))
//                        .padding(8.dp),
//                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
//                ) {
////                    post.location?.let {
////                        Text(
////                            text = "📍 $it",
////                            fontSize = 15.sp,
////                            modifier = Modifier.clickable {
////                                nav.navigateTo(Screen.MAP_SCREEN)
////                            }
////                        )
////                    }
//                    MapDialog(post, onDismiss = {
//                        nav.navigateTo(Screen.FEED)
//                    })
//
//                    Spacer(modifier = Modifier.weight(1f))
//
////                    Text(
////                        text = Instant.parse(post.createdAt).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss",)),
////                        fontSize = 14.sp,
////                    )
//                    Text(
//                        text = formatIsoDate(post.createdAt),
//                        fontSize = 14.sp,
//                    )
//                    Log.i("tempo", "PostItem: ${post.createdAt}" )
//                }
//            }
//
////            Image(
////                painter = painterResource(id = post.imageRes),
////                contentDescription = post.description,
////                contentScale = ContentScale.Crop,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(450.dp)
////            )
////
//            PostFullscreenImage(post.contentPicture)
//
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color(0xFFF0F0F0))
//                    .padding(12.dp)
//            ) {
//                Text(
//                    text = post.contentText,
//                    fontSize = 16.sp,
//                )
//            }
//        }
//    }
//
//}

//fun formatIsoDate(iso: String): String {
//    val dateTime = java.time.LocalDateTime.parse(iso)
//    val formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")
//    return dateTime.format(formatter)
//}



