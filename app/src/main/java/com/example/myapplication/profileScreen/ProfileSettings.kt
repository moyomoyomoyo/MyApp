package com.example.myapplication.profileScreen

import kotlinx.coroutines.launch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.ImagePicker
import com.example.myapplication.components.viewPicture
import com.example.myapplication.navigation.Header
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.ImagePicker
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.data.viewmodel.UserViewModel
import com.example.myapplication.navigation.Header
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen
import com.example.myapplication.components.LoadingScreen
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettings(
    user: UserEntity?,
    nav: NavigationViewModel,
    userViewModel: UserViewModel
) {
    if (user == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }
    val context = LocalContext.current
    var username by remember { mutableStateOf(user.username) }
    var bio by remember { mutableStateOf(user.bio) }
    var selectedDate by remember { mutableStateOf(user.dateOfBirth) }
    var profilePicBase64 by remember { mutableStateOf(user.profilePicture) }
    var showDatePicker by remember { mutableStateOf(false) }
    var isSaving by remember { mutableStateOf(false) }
    var showSuccessMessage by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val scrollState = rememberScrollState()

    // Verifica se ci sono modifiche
    val hasChanges = username != user.username ||
            bio != user.bio ||
            selectedDate != user.dateOfBirth ||
            profilePicBase64 != user.profilePicture

    Column(modifier = Modifier.fillMaxSize()) {
        // Header fisso
        Header(nav, user)

        // Contenuto scrollabile
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .verticalScroll(scrollState)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            // Card Immagine Profilo
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Profile Picture",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(16.dp))

                    Box(
                        modifier = Modifier.size(150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // Preview immagine profilo
                        Image(
                            bitmap = viewPicture(context, profilePicBase64).asImageBitmap(),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        // Overlay con pulsante cambia
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.4f)),
                            contentAlignment = Alignment.Center
                        ) {
                            ImagePicker(
                                modifier = Modifier.size(150.dp),
                                initialBase64 = profilePicBase64,
                                onImageSelected = { base64 ->
                                    profilePicBase64 = base64
                                }
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    Text(
                        "Tap to change",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Card Informazioni Account
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Account Information",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Username
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    Spacer(Modifier.height(16.dp))

                    // Data di nascita
                    OutlinedTextField(
                        value = selectedDate,
                        onValueChange = {},
                        label = { Text("Date of Birth") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { showDatePicker = true }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Select date"
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        readOnly = true
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Card Biografia
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Biography",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        "Tell people a bit about yourself",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = bio,
                        onValueChange = { bio = it },
                        placeholder = { Text("Write your bio...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        maxLines = 5,
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        "${bio.length}/150",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (bio.length > 150) Color.Red else Color.Gray,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Messaggio di successo
            AnimatedVisibility(visible = showSuccessMessage) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            "Profile updated successfully!",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Pulsante Save
            Button(
                onClick = {
                    if (!isSaving && hasChanges) {
                        isSaving = true
                        val updatedUser = user.copy(
                            username = username,
                            bio = bio,
                            dateOfBirth = selectedDate,
                            profilePicture = profilePicBase64
                        )

                        userViewModel.updateUser(updatedUser)

                        showSuccessMessage = true

                        // Nasconde il messaggio dopo 2 secondi
                        kotlinx.coroutines.GlobalScope.launch {
                            kotlinx.coroutines.delay(2000)
                            showSuccessMessage = false
                            isSaving = false
                        }

//                        // Torna al profilo dopo 2.5 secondi
//                        kotlinx.coroutines.GlobalScope.launch {
//                            kotlinx.coroutines.delay(2500)
//                            nav.navigateTo(Screen.PROFILE)
//                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                enabled = hasChanges && !isSaving,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7d0885),
                    disabledContainerColor = Color.Gray
                )
            ) {
                if (isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Save Changes",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Pulsante Cancel
            OutlinedButton(
                onClick = { nav.navigateBack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Cancel", fontSize = 16.sp)
            }

            Spacer(Modifier.height(24.dp))
        }
    }

    // DatePicker Dialog
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val millis = datePickerState.selectedDateMillis
                        if (millis != null) {
                            val date = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            selectedDate = date.format(
                                DateTimeFormatter.ofPattern("dd/MM/yyyy")
                            )
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}


//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileSettings(
//    user: UserEntity?,
//    nav: NavigationViewModel,
//    userViewModel: UserViewModel
//) {
//    if (user == null) {
//        LoadingScreen()
//    } else {
//
//        Header(nav)
//
//        var username by remember { mutableStateOf(user.username) }
//        var bio by remember { mutableStateOf(user.bio) }
//        var selectedDate by remember { mutableStateOf(user.dateOfBirth) }
//        var profilePicBase64 by remember { mutableStateOf(user.profilePicture) }
//
//        var showDatePicker by remember { mutableStateOf(false) }
//        val datePickerState = rememberDatePickerState()
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White)
//                .padding(bottom = 40.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            // immagine profilo
//            ImagePicker(
//                modifier = Modifier.size(150.dp),
//                initialBase64 = profilePicBase64,
//                onImageSelected = { base64 ->
//                    profilePicBase64 = base64
//                }
//            )
//
//            Spacer(Modifier.height(16.dp))
//
//            // username
//            OutlinedTextField(
//                value = username,
//                onValueChange = { username = it },
//                label = { Text("Username") },
//                modifier = Modifier
//                    .padding(10.dp)
//                    .fillMaxWidth(0.9f)
//            )
//
//            // data di nascita
//            OutlinedTextField(
//                value = selectedDate,
//                onValueChange = {},
//                label = { Text("Date of birth") },
//                modifier = Modifier
//                    .padding(10.dp)
//                    .fillMaxWidth(0.9f),
//                readOnly = true,
//                trailingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.DateRange,
//                        contentDescription = "Select date",
//                        modifier = Modifier.clickable { showDatePicker = true }
//                    )
//                }
//            )
//
//            if (showDatePicker) {
//                DatePickerDialog(
//                    onDismissRequest = { showDatePicker = false },
//                    confirmButton = {
//                        TextButton(
//                            onClick = {
//                                val millis = datePickerState.selectedDateMillis
//                                if (millis != null) {
//                                    val date = Instant.ofEpochMilli(millis)
//                                        .atZone(ZoneId.systemDefault())
//                                        .toLocalDate()
//                                    selectedDate = date.format(
//                                        DateTimeFormatter.ofPattern("dd/MM/yyyy")
//                                    )
//                                }
//                                showDatePicker = false
//                            }
//                        ) { Text("OK") }
//                    },
//                    dismissButton = {
//                        TextButton(onClick = { showDatePicker = false }) {
//                            Text("Close")
//                        }
//                    }
//                ) {
//                    DatePicker(state = datePickerState)
//                }
//            }
//
//            // biografia
//            OutlinedTextField(
//                value = bio,
//                onValueChange = { bio = it },
//                label = { Text("Biografy") },
//                modifier = Modifier
//                    .padding(10.dp)
//                    .fillMaxWidth(0.9f)
//            )
//
//            Spacer(Modifier.height(20.dp))
//
//            // salvataggio modifiche
//            Button(
//                onClick = {
//                    val updatedUser = user.copy(
//                        username = username,
//                        bio = bio,
//                        dateOfBirth = selectedDate,
//                        profilePicture = profilePicBase64
//                    )
//
//                    userViewModel.updateUser(updatedUser)
//
//                    nav.navigateTo(Screen.PROFILE)
//                },
//                modifier = Modifier
//                    .fillMaxWidth(0.6f)
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF7d0885)
//                )
//            ) {
//                Text("Save", color = Color.White)
//            }
//        }
//    }
//}


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
