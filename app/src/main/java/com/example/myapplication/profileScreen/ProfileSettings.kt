package com.example.myapplication.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.navigation.Header
import com.example.myapplication.navigation.NavigationViewModel
import java.time.ZoneId

data class User(
    val id: String,
    val createdAt: Int,
    var username: String,
    var bio: String,
    var dateOfBirth: String,
    val profilePicture: String,
    val isYourFollower: Int,
    val isYourFollowing: Int,
    val followersCount: Int,
    val followingCount: Int,
    val postsCount: Int
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettings(nav: NavigationViewModel) {
    Header(nav)

    val user = User(
        id = "1",
        createdAt = 2020,
        username = "moyo",
        bio = "Finding beauty in the everyday ✨",
        dateOfBirth = "12/04/2003",
        profilePicture = "pf",
        isYourFollower = 1,
        isYourFollowing = 1,
        followersCount = 150,
        followingCount = 200,
        postsCount = 75
    )

    // Stati dei campi modificabili
    var username by remember { mutableStateOf(user.username) }
    var bio by remember { mutableStateOf(user.bio) }
    var selectedDate by remember { mutableStateOf(user.dateOfBirth) }

    // Stato del dialog
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileImage(imageRes = R.drawable.pf)

        // USERNAME
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.9f)
        )

        // DATA DI NASCITA CON DATE PICKER
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            label = { Text("Data di nascita") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.9f),
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Seleziona data",
                    modifier = Modifier.clickable {
                        showDatePicker = true
                    })// <-- SOLO L’ICONA È CLICCABILE )
            })

        // Dialog vero e proprio
        if (showDatePicker) {
            DatePickerDialog(onDismissRequest = { showDatePicker = false }, confirmButton = {
                TextButton(
                    onClick = {
                        val millis = datePickerState.selectedDateMillis
                        if (millis != null) {
                            val date = java.time.Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault()).toLocalDate()

                            selectedDate = date.format(
                                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
                            )
                        }
                        showDatePicker = false
                    }) {
                    Text("OK")
                }
            }, dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Annulla")
                }
            }) {
                DatePicker(state = datePickerState)
            }
        }

        // BIOGRAFIA
        OutlinedTextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text("Biografia") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(20.dp))


        // SAVE BUTTON
        TextButton(
            onClick = {
                // Qui puoi chiamare il tuo ViewModel
//                nav.saveProfile(
//                    username,
//                    selectedDate,
//                    bio
//                )
                user.username = username
                user.dateOfBirth = selectedDate
                user.bio = bio

                println("SALVATO:")
                println ("Username: $username")
                println ("Data di nascita: $selectedDate")
                println ("Bio: $bio")
            },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp)
                .background(
                    Color(0xFF7d0885),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                ),
        ) { Text(text = "Salva modifiche", color = Color.White, fontWeight = FontWeight.Bold) }


    }
}


//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.text.input.TextFieldLineLimits
//import androidx.compose.foundation.text.input.rememberTextFieldState
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.DateRange
//import androidx.compose.material3.DatePicker
//import androidx.compose.material3.DatePickerDefaults
//import androidx.compose.material3.DatePickerDialog
//import androidx.compose.material3.DatePickerState
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TextField
//import androidx.compose.material3.rememberDatePickerState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.TransformOrigin
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.Density
//import androidx.compose.ui.unit.dp
//import com.example.myapplication.R
//import com.example.myapplication.navigation.Header
//import com.example.myapplication.navigation.NavigationViewModel
//import java.time.LocalDateTime
//import java.time.ZoneId
//import kotlin.time.Instant
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileSettings(nav: NavigationViewModel) {
//    Header(nav)
//
//    val user = User(
//        id = "1",
//        createdAt = 2020,
//        username = "moyo",
//        bio = "Finding beauty in the everyday ✨",
//        dateOfBirth = "12/04/2003",
//        profilePicture = "pf",
//        isYourFollower = 1,
//        isYourFollowing = 1,
//        followersCount = 150,
//        followingCount = 200,
//        postsCount = 75
//    )
//
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFFFFFFFF)),
//        horizontalAlignment = Alignment.CenterHorizontally,
//
//        ) {
//
//        ProfileImage(imageRes = R.drawable.pf)
//
//        OutlinedTextField(
//            value = user.username,
//            onValueChange = {},
//            label = { Text("Username") },
//            modifier = Modifier
//                .padding(10.dp)
//                .fillMaxWidth(0.9f)
//        )
//
//        val datePickerState = rememberDatePickerState()
//
//        // Create a formatter
//        val dateFormatter = remember { DatePickerDefaults.dateFormatter() }
//
//        CompositionLocalProvider() {
//            DatePicker(
//                state = datePickerState,
//                dateFormatter = dateFormatter,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .graphicsLayer(
//                        scaleX = 1f,
//                        scaleY = 1f,
//                        transformOrigin = TransformOrigin(0f, 0f)
//                    )
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//
////        Row(
////            verticalAlignment = Alignment.CenterVertically,
////            modifier = Modifier.padding(8.dp)
////        ) {
////            Icon(
////                imageVector = Icons.Filled.DateRange,
////                contentDescription = "Data di nascita"
////            )
////            Text(
////                text = "12/04/2003",
////            )
////        }
//
//        OutlinedTextField(
//            value = user.bio,
//            onValueChange = {},
//            label = { Text("Biografy") },
//            modifier = Modifier
//                .padding(10.dp)
//                .fillMaxWidth(0.9f)
//        )
////        Text("Finding beauty in the everyday ✨", textAlign = TextAlign.Center)
//    }
//
//}
//
