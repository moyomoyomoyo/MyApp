package com.example.myapplication.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.R

fun viewPicture(context: Context, image: String): Bitmap {

    val newImage: String

    newImage = if(image.isEmpty()){
        encodeResourceToBase64(context, R.drawable.user)
    } else
        image


    // decodifica l'immagine da base64 a bitmap
    val decodedBytes = Base64.decode(newImage, Base64.DEFAULT)
    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

    return bitmap
}