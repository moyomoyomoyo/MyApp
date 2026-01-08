package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState

@Composable
fun LocationPicker(
    modifier: Modifier = Modifier,
    startLocation: Point = Point.fromLngLat(9.2319522, 45.4759249),
    onLocationSelected: (Point) -> Unit
) {
    var selectedLocation by remember { mutableStateOf<Point?>(null) }

    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            zoom(15.0)
            center(startLocation)
            pitch(0.0)
            bearing(0.0)
        }
    }

    Column(modifier = modifier) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            MapboxMap(
                modifier = Modifier.fillMaxSize(),
                mapViewportState = mapViewportState
            )

            // Marker fisso al centro
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Center marker",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(48.dp)
                    .offset(y = (-24).dp),
                tint = Color.Red
            )

            // Pulsante per confermare la posizione
            Button(
                onClick = {
                    val center = mapViewportState.cameraState?.center
                    if (center != null) {
                        selectedLocation = center
                        onLocationSelected(center)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save location"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Posizione selezionata:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        selectedLocation?.let { loc ->
            Text(
                text = "Lat: ${loc.latitude()}, Lng: ${loc.longitude()}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun LocationPickerDialog(onDismiss: () -> Unit, onLocationSelected: (Point) -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 6.dp,
            modifier = Modifier.padding(15.dp)
        ) {
            LocationPicker(
                modifier = Modifier,
                onLocationSelected = { point ->
                    onLocationSelected(point)
                    onDismiss()
                })
        }
    }
}