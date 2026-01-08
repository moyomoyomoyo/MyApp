package com.example.myapplication.feed.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.components.viewPicture
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.feed.model.FeedPostWrapper
import com.example.myapplication.map.MapboxMapScreen
import com.example.myapplication.profileScreen.ProfileImage
import com.mapbox.maps.extension.style.expressions.dsl.generated.properties

@Composable
fun FeedPostItem(
    postWrapper: FeedPostWrapper,
    onClick: () -> Unit
) {

    val context = LocalContext.current
    var showMapDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Informazioni autore
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileImage("", 30.dp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = postWrapper.author?.username ?: "Utente sconosciuto",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "• ${formatDate(postWrapper.post.createdAt)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Immagine del post (se presente)
//            if (postWrapper.post.contentPicture.isNotEmpty()) {

                Image(
                    bitmap = viewPicture(context, postWrapper.post.contentPicture).asImageBitmap(),
                    contentDescription = "Immagine del post",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,

                )



//                PostImage(
//                    base64Image = postWrapper.post.contentPicture,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                )
                Spacer(modifier = Modifier.height(8.dp))
//            }

            // Contenuto testuale del post
            if (postWrapper.post.contentText.isNotEmpty()) {
                Text(
                    text = postWrapper.post.contentText,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Località
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    showMapDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Località",
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = formatLocation(postWrapper.post.location),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            if (showMapDialog) {
                Dialog(onDismissRequest = {
                    showMapDialog = false},
                    properties = DialogProperties(usePlatformDefaultWidth = false)
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    ) {
                        MapboxMapScreen(
                            postWrapper.post.location.latitude,
                            postWrapper.post.location.longitude,
                        )
                    }
                }
            }


//            // Località
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.LocationOn,
//                    contentDescription = "Località",
//                    tint = Color.Gray,
//                    modifier = Modifier.size(16.dp)
//                )
//                Spacer(modifier = Modifier.width(4.dp))
//                Text(
//                    text = formatLocation(postWrapper.post.location),
//                    style = MaterialTheme.typography.bodySmall,
//                    color = Color.Gray
//                )
//            }
        }
    }
}
