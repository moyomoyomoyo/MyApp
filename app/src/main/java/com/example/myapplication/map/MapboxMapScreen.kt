package com.example.myapplication.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.myapplication.navigation.NavigationViewModel
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.dsl.generated.distance
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.locationcomponent.location
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@SuppressLint("MissingPermission")
@Composable
fun MapboxMapScreen(
    postLatitude: Double,
    postLongitude: Double,
//    nav: NavigationViewModel
) {
    val context = LocalContext.current
    var mapView: MapView? by remember { mutableStateOf(null) }
    var userLocation by remember { mutableStateOf<Location?>(null) }
    var hasLocationPermission by remember { mutableStateOf(false) }
    var showUserMarker by remember { mutableStateOf(false) }



    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // Calcola distanza in km
    fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371.0 // Raggio della Terra in km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c
    }

    // Launcher per richiedere permessi
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasLocationPermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (hasLocationPermission) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    userLocation = it
                    val distance = calculateDistance(
                        it.latitude, it.longitude,
                        postLatitude, postLongitude
                    )
                    showUserMarker = distance <= 5.0
                }
            }
        }
    }

    // Controlla permessi all'avvio
    LaunchedEffect(Unit) {
        val hasFineLocation = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasCoarseLocation = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        hasLocationPermission = hasFineLocation || hasCoarseLocation

        if (hasLocationPermission) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    userLocation = it
                    val distance = calculateDistance(
                        it.latitude, it.longitude,
                        postLatitude, postLongitude
                    )
                    showUserMarker = distance <= 5.0
                    Log.i("MapboxMapScreen", "User is $distance km away from post.")
                }
            }
        } else {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }
    Log.i("MapboxMapScreen", "User is $userLocation km away from post.")

    Box(modifier = Modifier.fillMaxSize()) {
        // Mappa Mapbox
        AndroidView(
            factory = { ctx ->
                MapView(ctx).apply {
                    mapView = this
                    getMapboxMap().apply {
                        // Carica lo stile della mappa
                        loadStyleUri(Style.MAPBOX_STREETS) { style ->
                            // Crea annotation manager per i marker
                            val annotationApi = annotations
                            val pointAnnotationManager = annotationApi.createPointAnnotationManager()

                            // Marker per il post (rosso)
                            val postMarker = PointAnnotationOptions()
                                .withPoint(Point.fromLngLat(postLongitude, postLatitude))
                                .withIconImage("red-marker")
                                .withIconSize(1.5)

                            pointAnnotationManager.create(postMarker)

                            // Marker per l'utente (blu) se nelle vicinanze
                            if (showUserMarker && userLocation != null) {
                                val userMarker = PointAnnotationOptions()
                                    .withPoint(Point.fromLngLat(userLocation!!.longitude, userLocation!!.latitude))
                                    .withIconImage("blue-marker")
                                    .withIconSize(1.5)

                                pointAnnotationManager.create(userMarker)
                            }

                            // Centra la camera sul post o tra post e utente
                            val cameraPoint = if (showUserMarker && userLocation != null) {
                                // Centro tra post e utente
                                Point.fromLngLat(
                                    (postLongitude + userLocation!!.longitude) / 2,
                                    (postLatitude + userLocation!!.latitude) / 2
                                )
                            } else {
                                Point.fromLngLat(postLongitude, postLatitude)
                            }

                            val cameraOptions = CameraOptions.Builder()
                                .center(cameraPoint)
                                .zoom(if (showUserMarker) 12.0 else 14.0)
                                .build()

                            setCamera(cameraOptions)
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Header overlay
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .align(Alignment.TopCenter),
//            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//            shape = RoundedCornerShape(16.dp)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(12.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                IconButton(onClick = { nav.navigateBack() }) {
//                    Icon(
//                        imageVector = Icons.Outlined.KeyboardArrowLeft,
//                        contentDescription = "Back",
//                        modifier = Modifier.size(28.dp)
//                    )
//                }

//                Text(
//                    text = "Post Location",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold
//                )
//
//                Box(modifier = Modifier.size(28.dp))
//            }
//        }

        // Info card in basso
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Info post location
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(Color.Red, CircleShape)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(
                            "Post Location",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Lat: ${String.format("%.4f", postLatitude)}, Lon: ${String.format("%.4f", postLongitude)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }

                // Info user location se vicino
                if (showUserMarker && userLocation != null) {
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(Color.Blue, CircleShape)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(
                                "Your Location",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            val distance = calculateDistance(
                                userLocation!!.latitude, userLocation!!.longitude,
                                postLatitude, postLongitude
                            )
                            Text(
                                "Distance: ${String.format("%.2f", distance)} km",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                } else if (hasLocationPermission && userLocation != null) {
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "You are more than 5 km away",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                // Pulsante per aprire in Google Maps
//                Button(
//                    onClick = {
//                        val intent = android.content.Intent(
//                            android.content.Intent.ACTION_VIEW,
//                            android.net.Uri.parse("geo:$postLatitude,$postLongitude?q=$postLatitude,$postLongitude")
//                        )
//                        context.startActivity(intent)
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(12.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.LocationOn,
//                        contentDescription = null,
//                        modifier = Modifier.size(20.dp)
//                    )
//                    Spacer(Modifier.width(8.dp))
//                    Text("Open in Google Maps")
//                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mapView?.onDestroy()
        }
    }
}