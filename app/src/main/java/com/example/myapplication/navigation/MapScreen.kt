package com.example.myapplication.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.R
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.post.Post
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage

@Composable
fun MapDialog(post: PostEntity, onDismiss: () -> Unit) {
    var showMapDialog by remember { mutableStateOf(false) }

    Text(
        text = "Open Map",
        color = Color.Blue,
        fontSize = 15.sp,
        modifier = Modifier.clickable {
            showMapDialog = true
        }
    )

    if (showMapDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {

                MapScreenContent(
                    lon = post.location.longitude,
                    lat = post.location.latitude
                )

                IconButton(
                    onClick = { showMapDialog = false },
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close fullscreen",
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }


    }
}

@Composable
fun MapScreenContent(lon: Double, lat: Double) {

    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            center(Point.fromLngLat(lon, lat))
            zoom(14.0)
        }
    }

    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        mapViewportState = mapViewportState
    ) {
        val marker = rememberIconImage(
            key = "location_marker",
            painter = rememberVectorPainter(
                Icons.Default.LocationOn
            )
        )

        PointAnnotation(
            point = Point.fromLngLat(lon, lat)
        ) {
            iconImage = marker
            iconSize = 2.0
        }
    }
}
