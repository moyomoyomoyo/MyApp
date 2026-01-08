package com.example.myapplication.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.ByteArrayOutputStream
import androidx.core.graphics.scale

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    initialBase64: String = "",
    isPost: Boolean = false,
    onImageSelected: (String) -> Unit
) {
    val context = LocalContext.current


    var base64Image by remember { mutableStateOf(initialBase64) }

    // Se c’è un default → restituisco SUBITO una String NON nulla
    LaunchedEffect(initialBase64) {
        onImageSelected(initialBase64)
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri == null) return@rememberLauncherForActivityResult

        try {
            val newBase64 = encodeUriToBase64(context, uri)
            base64Image = newBase64
            onImageSelected(newBase64)
        } catch (_: Exception) {
            // Nessun fallback → non cambiare nulla
        }
    }

    Card(
        modifier = modifier
            .size(if (isPost) 450.dp else 120.dp)
            .clickable { launcher.launch("image/*") },
        shape = RoundedCornerShape(if (isPost) 0.dp else 100.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        when (val img = base64Image) {
            "" -> {
                // Nessuna immagine → icona "+"
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFEAEAEA)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add image",
                        tint = Color.Gray,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            else -> {
                val bytes = Base64.decode(img, Base64.NO_WRAP)
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Selected Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


/* ---------------------- FUNZIONI DI SUPPORTO ---------------------- */

fun encodeResourceToBase64(context: Context, resId: Int): String {
    val bitmap = BitmapFactory.decodeResource(context.resources, resId)
    val out = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out)
    return Base64.encodeToString(out.toByteArray(), Base64.NO_WRAP)
}

fun encodeUriToBase64(context: Context, uri: Uri): String {
    val stream = context.contentResolver.openInputStream(uri)
    val bitmap = BitmapFactory.decodeStream(stream)
        ?: throw IllegalArgumentException("Impossibile decodificare l'immagine")
    stream?.close()

    val scale = minOf(600f / bitmap.width, 600f / bitmap.height)
    val resized = bitmap.scale((bitmap.width * scale).toInt(), (bitmap.height * scale).toInt())

    val out = ByteArrayOutputStream()
    resized.compress(Bitmap.CompressFormat.JPEG, 70, out)
    return Base64.encodeToString(out.toByteArray(), Base64.NO_WRAP)
}
