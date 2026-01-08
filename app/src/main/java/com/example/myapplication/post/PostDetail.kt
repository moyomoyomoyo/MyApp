package com.example.myapplication.post

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.viewmodel.UserViewModel
import com.example.myapplication.navigation.NavigationViewModel

@Composable
fun PostDetail(postId: Int, nav: NavigationViewModel, uvm: UserViewModel) {
    // Osserva il post dal ViewModel
    val post by uvm.postById.collectAsState()
    // Carica il post quando il composable viene mostrato
    LaunchedEffect(postId) { uvm.getPostById(postId) }

    post?.let{ post -> PostDetailContent(post) } ?: run{
        Text(
            text = "Post not found",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }


}

@Composable
fun PostDetailContent(post: PostEntity) {

    Column(modifier = Modifier.padding(16.dp)) {
//        Image(
//            bitmap = imagePicture(post.contentPicture).asImageBitmap(),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp),
//            contentScale = ContentScale.Crop
//        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "moyo " + post.contentText,
            style = MaterialTheme.typography.bodyLarge
        )

    }
}


//fun imagePicture(image: String): Bitmap {
//    // decodifica l'immagine da base64 a bitmap
//    val decodedBytes = Base64.decode(image, Base64.DEFAULT)
//    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
//
//    return bitmap
//}