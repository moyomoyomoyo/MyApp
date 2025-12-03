package com.example.myapplication.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.navigation.NavBar

@Composable
fun ProfileScreen(modifier: Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
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

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
            )


            Text(
                text = "@nome",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "data nascita",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "bio",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "modifica profilo",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "post follower following",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )
        }
    }

    NavBar(modifier)


}