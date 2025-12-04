package com.example.myapplication.profileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PostDetail(post: Post) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = post.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = post.description,
            style = MaterialTheme.typography.bodyLarge
        )

        post.location?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "📍 $it",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
