package com.example.myapplication.profileScreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.components.encodeResourceToBase64
import com.example.myapplication.components.encodeUriToBase64
import com.example.myapplication.components.viewPicture


@Composable
fun ProfileImage(
    base64Image: String,
    size: Dp = 120.dp,
    isFriend: Boolean = true
) {

//    val bitmap: ImageBitmap
    val context = LocalContext.current
//    if(base64Image.isNullOrEmpty()){
////        val defaultImageBase64 = encodeResourceToBase64(LocalContext.current, R.drawable.user)
//        bitmap = viewPicture(context, defaultImageBase64).asImageBitmap()
//    } else {
        // decodifica l'immagine da base64 a bitmap
//        val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
        val bitmap = viewPicture(context, base64Image).asImageBitmap()
//    }



    Image(
        bitmap = bitmap,
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(size)
            .then(
                if (isFriend) {
                    Modifier.border(
                        width = 3.dp, color = Color(0xFF7d0885), shape = CircleShape
                    )
                } else {
                    Modifier
                }
            )
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )

//    val bytes = remember(base64Image) {
//        try {
//            android.util.Base64.decode(base64Image, android.util.Base64.DEFAULT)
//        } catch (e: Exception) {
//            null
//        }
//    }
//
//    val bitmap = remember(bytes) {
//        bytes?.let { android.graphics.BitmapFactory.decodeByteArray(it, 0, it.size) }
//    }
//
//    if (bitmap != null) {
//        Image(
//            bitmap = bitmap.asImageBitmap(),
//            contentDescription = null,
//            modifier = Modifier
//                .size(size)
//                .then(
//                    if (isFriend) {
//                        Modifier.border(3.dp, Color(0xFF7d0885), CircleShape)
//                    } else Modifier
//                )
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop
//        )
//    } else {
//        // fallback
//        Box(
//            modifier = Modifier
//                .size(size)
//                .clip(CircleShape)
//                .border(3.dp, Color(0xFF7d0885), CircleShape)
//                .background(Color.LightGray)
//        )
//    }
}

//    Image(
//        painter = rememberAsyncImagePainter(model = "data:image/png;base64,$base64Image"),
//        contentDescription = "Profile Image",
//        modifier = Modifier
//            .size(size)
//            .then(
//                if (isFriend) {
//                    Modifier.border(
//                        width = 3.dp, color = Color(0xFF7d0885), shape = CircleShape
//                    )
//                } else {
//                    Modifier
//                }
//            )
//            .clip(CircleShape),
//        contentScale = ContentScale.Crop
//    )
//}
