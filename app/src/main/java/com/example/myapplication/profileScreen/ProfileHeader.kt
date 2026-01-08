package com.example.myapplication.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.ViewModeToggle
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.navigation.Header
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen


enum class ViewMode {
    LIST, GRID
}

@Composable
fun ProfileHeader(
    user: UserEntity,
    nav: NavigationViewModel,
    viewMode: ViewMode,
    onViewModeChange: (ViewMode) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(nav, user)

        ProfileImage(user.profilePicture)

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            "@${user.username}",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            if (user.dateOfBirth.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Data di nascita"
                )
                Text(user.dateOfBirth)
            } else {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Data di nascita",
                    tint = Color.Gray
                )
                Text(
                    " add your date of birth",
                    color = Color.Gray
                )
            }
        }

        if (user.bio.isNotEmpty()) {
            Text(
                user.bio,
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                "add a biography",
                color = Color.Gray
            )
        }

        Button(
            onClick = { nav.navigateTo(Screen.PROFILE_SETTINGS) },
            modifier = Modifier.padding(15.dp)
        ) {
            Text("Edit Profile")
        }

        ProfileStats(
            user.postsCount,
            user.followersCount,
            user.followingCount
        )

        // Toggle per cambiare vista
        ViewModeToggle(
            currentMode = viewMode,
            onModeChange = onViewModeChange
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = Color(0xFFE0E0E0)
        )
    }
}