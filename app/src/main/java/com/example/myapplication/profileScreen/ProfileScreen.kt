package com.example.myapplication.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun ProfileScreen(modifier: Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()

    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            ProfileImage(imageRes = R.drawable.pf)

            Text("@moyo", textAlign = TextAlign.Center)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Data di nascita"
                )
                Text(
                    text = "12/04/2003",   // la tua data di nascita
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text("Finding beauty in the everyday ✨", textAlign = TextAlign.Center)

            Button(
                onClick = {  },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Edit Profile")
            }

            ProfileStats(
                posts = 12,
                followers = 340,
                following = 198
            )
        }

        Column(
            modifier = Modifier
            .background(Color(0xFFe5d3e5)),
        ) {
            val posts = listOf(
                Post(R.drawable.pf, "Prima foto"),
                Post(R.drawable.pf, "Tramonto"),
                Post(R.drawable.pf, "Selfie")
            )

            PostGrid(posts) { post ->

            }
        }





    }




}