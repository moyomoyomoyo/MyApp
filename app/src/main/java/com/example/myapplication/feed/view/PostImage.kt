package com.example.myapplication.feed.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.components.encodeResourceToBase64
import com.example.myapplication.components.viewPicture
import com.example.myapplication.data.entity.Location


// Componente per visualizzare l'immagine con gestione errori
@Composable
fun PostImage(
    base64Image: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isError by remember { mutableStateOf(false) }
//    val defaultImageBase64 = encodeResourceToBase64(LocalContext.current, R.drawable.user)
//    val place = viewPicture(defaultImageBase64).asImageBitmap()


    LaunchedEffect(base64Image) {
        bitmap = try {
            viewPicture(context, base64Image)
        } catch (e: Exception) {
            isError = true
            null
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when {
            bitmap != null -> {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = "Immagine del post",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            isError -> {
                // Placeholder per immagine non valida
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                        Image(
//                            bitmap = place,
//                            contentDescription = "Immagine non valida",
//                            modifier = Modifier.size(48.dp)
//                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Immagine non valida",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
            else -> {
                // Loading
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

//// Funzione per decodificare Base64 a Bitmap
//private fun viewPicture(image: String): Bitmap {
//    val decodedBytes = Base64.decode(image, Base64.DEFAULT)
//    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
//    return bitmap
//}

// Funzioni di utilità
fun formatDate(dateString: String): String {
    return try {
        // Adatta al tuo formato di data
        // Esempio: "2024-01-15T10:30:00" -> "15 Gen"
        dateString.take(10) // Semplificato, personalizza secondo necessità
    } catch (e: Exception) {
        dateString
    }
}

fun formatLocation(location: Location): String {
    return "%.4f, %.4f".format(location.latitude, location.longitude)
    // Oppure usa Geocoder per ottenere il nome della località
}
