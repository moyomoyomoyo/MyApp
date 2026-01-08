package com.example.myapplication.post

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.LoadingScreen
import com.example.myapplication.data.entity.PostEntity

@Composable
fun PostGridItem(
    post: PostEntity,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clickable(onClick = onClick)
    ) {
//        // Immagine del post
//        Image(
//            bitmap = imagePicture(post.contentPicture).asImageBitmap(),
//            contentDescription = "Post thumbnail",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        ) ?: run {
            // Placeholder se non c'è immagine
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "No image",
                    tint = Color.Gray
                )
            }
        }

        // Overlay con statistiche (opzionale)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
                .alpha(0f) // Diventa visibile on hover in desktop, qui sempre invisibile
        ) {
//            Row(
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .padding(8.dp),
//                horizontalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        imageVector = Icons.Filled.Favorite,
//                        contentDescription = "Likes",
//                        tint = Color.White,
//                        modifier = Modifier.size(20.dp)
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text(
//                        "${post.likesCount}",
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold
//                    )
//                }

//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        imageVector = Icons.Filled.Comment,
//                        contentDescription = "Comments",
//                        tint = Color.White,
//                        modifier = Modifier.size(20.dp)
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text(
//                        "${post.commentsCount}",
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold
//                    )
//                }
//            }
        }
    }
//}
//fun PostGridItem(post: PostEntity?, onPostClick: (PostEntity) -> Unit) {
//    if (post == null) {
//        LoadingScreen()
//        return
//    }
//    // decodifica l'immagine da base64 a bitmap
//    val decodedBytes = Base64.decode(post.contentPicture, Base64.DEFAULT)
//    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
//
//    Image(
//        bitmap = bitmap.asImageBitmap(),
//        contentDescription = null,
//        modifier = Modifier
//            .padding(2.dp)
//            .aspectRatio(1f)
//            .clickable { onPostClick(post) },
//        contentScale = ContentScale.Crop
//    )
//}
