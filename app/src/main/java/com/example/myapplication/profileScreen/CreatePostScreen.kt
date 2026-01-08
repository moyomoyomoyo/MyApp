package com.example.myapplication.profileScreen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

import com.example.myapplication.components.ImagePicker
import com.example.myapplication.components.LocationPickerDialog
import com.example.myapplication.components.viewPicture
import com.example.myapplication.data.entity.Location
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.viewmodel.UserViewModel
import com.example.myapplication.navigation.Header
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen
import com.mapbox.geojson.Point
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CreatePostScreen(nav: NavigationViewModel, uvm: UserViewModel) {
    var description by remember { mutableStateOf("") }
    var base64Image by remember { mutableStateOf<String?>(null) }
    var selectedPoint by remember { mutableStateOf<Point?>(null) }
    var showLocationDialog by remember { mutableStateOf(false) }
    var isPosting by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // Verifica se tutti i campi sono validi
    val canPost = !base64Image.isNullOrEmpty() && description.isNotBlank()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header fisso
        Header(nav)

        // Contenuto scrollabile
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Card con preview immagine
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    ImagePicker(isPost = true) { img ->
                        base64Image = img
                    }

                    if (base64Image != null) {
//                        Image(
//                            bitmap = viewPicture(context, "").asImageBitmap(),
//                            contentDescription = "Selected image",
//                            modifier = Modifier.fillMaxSize(),
//                            contentScale = ContentScale.Crop
//                        )
//
                        // Overlay per cambiare immagine
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.3f))
                        )

                        ImagePicker(isPost = true) { img ->
                            base64Image = img
                        }
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ImagePicker(isPost = true) { img ->
                                base64Image = img
                            }
                            Spacer(Modifier.height(16.dp))
                            Text(
                                "Add a photo",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Card con form
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Caption",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        placeholder = { Text("Write a caption...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        maxLines = 5,
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        "${description.length}/500",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (description.length > 500) Color.Red else Color.Gray
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Card Location
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedPoint != null)
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Location",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            if (selectedPoint != null) {
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    "Lat: ${String.format("%.4f", selectedPoint!!.latitude())}, Lon: ${String.format("%.4f", selectedPoint!!.longitude())}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            } else {
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    "Optional",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                        }

                        FilledTonalButton(
                            onClick = { showLocationDialog = true },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = if (selectedPoint != null)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.secondaryContainer
                            )
                        ) {
                            Icon(
                                imageVector = if (selectedPoint != null) Icons.Filled.Check else Icons.Filled.LocationOn,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(if (selectedPoint != null) "Added" else "Add")
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Pulsante Post
            Button(
                onClick = {
                    if (canPost && !isPosting) {
                        isPosting = true
                        val currentDateTime = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

                        uvm.insertPost(
                            PostEntity(
                                authorId = 1, // TODO: Usa l'ID dell'utente corrente
                                createdAt = currentDateTime.format(formatter),
                                contentPicture = base64Image ?: "",
                                contentText = description,
                                location = Location(
                                    latitude = selectedPoint?.latitude() ?: 0.0,
                                    longitude = selectedPoint?.longitude() ?: 0.0
                                )
                            )
                        )
                        nav.navigateTo(Screen.FEED)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = canPost && !isPosting,
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isPosting) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Share Post", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(Modifier.height(16.dp))

            // Pulsante Annulla
            OutlinedButton(
                onClick = { nav.navigateBack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Cancel", fontSize = 16.sp)
            }

            Spacer(Modifier.height(32.dp))
        }
    }

    // Dialog per selezionare location
    if (showLocationDialog) {
        LocationPickerDialog(
            onDismiss = { showLocationDialog = false },
            onLocationSelected = { point ->
                selectedPoint = point
            }
        )
    }
}


//package com.example.myapplication.profileScreen
//
//import androidx.compose.ui.graphics.Color
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.myapplication.components.ImagePicker
//import com.example.myapplication.components.LocationPickerDialog
//import com.example.myapplication.data.entity.Location
//import com.example.myapplication.data.entity.PostEntity
//import com.example.myapplication.data.viewmodel.UserViewModel
//import com.example.myapplication.navigation.Header
//import com.example.myapplication.navigation.NavigationViewModel
//import com.example.myapplication.navigation.Screen
//import com.mapbox.geojson.Point
//import com.mapbox.maps.extension.style.expressions.dsl.generated.id
//import java.time.LocalDateTime
//
//@Composable
//fun CreatePostScreen(nav: NavigationViewModel, uvm: UserViewModel) {
//
//    var description by remember { mutableStateOf("") }
//    var location by remember { mutableStateOf("") }
//    var lat by remember { mutableStateOf("") }
//    var lon by remember { mutableStateOf("") }
//    var base64Image by remember { mutableStateOf<String?>(null) }
//    var selectedPoint by remember { mutableStateOf<Point?>(null) }
//
//    var showLocationDialog by remember { mutableStateOf(false) }
//
//    Header(nav)
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
////            .background(Color(0xFFe5d3e5))
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        ImagePicker(isPost = true) { img ->
//            base64Image = img
//        }
//
//        Spacer(Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = description,
//            onValueChange = { description = it },
//            label = { Text("Add caption") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(Modifier.height(16.dp))
//
//        Button(
//            onClick = { showLocationDialog = true },
//            modifier = Modifier
//                .fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = if (lon.isEmpty()) MaterialTheme.colorScheme.primary else Color(
//                    0xFFba87ba
//                ),
//            )
//
//        ) {
//            Text(
//                if (lat.isEmpty())
//                    "Add Location"
//                else
//                    "Location added!"
//            )
//        }
//
//        // --- DIALOG MAPPA ---
//        if (showLocationDialog) {
//            LocationPickerDialog(
//                onDismiss = { showLocationDialog = false },
//                onLocationSelected = { point ->
//                    selectedPoint = point
//                    lat = point.latitude().toString()
//                    lon = point.longitude().toString()
////                    location = "${lat}, ${lon}"
//                }
//            )
//        }
//        // ---------------------
//
//        Spacer(Modifier.height(16.dp))
//        Button(
//            onClick = {
//                uvm.insertPost(
//                    PostEntity(
//                        authorId = 1,
//                        createdAt = LocalDateTime.now().toString(),
//                        contentPicture = base64Image ?: "",
//                        contentText = description,
//                        location = Location(
//                            latitude = lat.toDoubleOrNull() ?: 0.0,
//                            longitude = lon.toDoubleOrNull() ?: 0.0
//                        ),
//                    )
//                )
//                nav.navigateTo(Screen.FEED)
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Create Post")
//        }
//    }
//}
//
//
////
////@Composable
////fun CreatePostScreen(nav: NavigationViewModel) {
////
////    var description by remember { mutableStateOf("") }
////    var location by remember { mutableStateOf("") }
////    var base64Image by remember { mutableStateOf<String?>(null) }
////
////    Header(nav)
////
////    Column(
////        modifier = Modifier
////            .fillMaxSize()
////            .background(Color(0xFFe5d3e5))
////            .padding(16.dp),
////        horizontalAlignment = Alignment.CenterHorizontally
////    ) {
////
////        ImagePicker(
////            onImageSelected = { img ->
////                base64Image = img
////            }
////        )
////
////        Spacer(modifier = Modifier.height(16.dp))
////
////        OutlinedTextField(
////            value = description,
////            onValueChange = { description = it },
////            label = { Text("Descrizione") },
////            modifier = Modifier.fillMaxWidth()
////        )
////
////        Spacer(modifier = Modifier.height(8.dp))
////
////        OutlinedTextField(
////            value = location,
////            onValueChange = { location = it },
////            label = { Text("Location") },
////            modifier = Modifier.fillMaxWidth()
////        )
////
////        Spacer(modifier = Modifier.height(16.dp))
////
////        Button(
////            onClick = {
////                // description, location, base64Image
////            },
////            modifier = Modifier.fillMaxWidth()
////        ) {
////            Text("Crea Post")
////        }
////    }
////}
