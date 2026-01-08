package com.example.myapplication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.ImagePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.components.InputCounter
import com.example.myapplication.components.encodeResourceToBase64
import com.example.myapplication.components.viewPicture

@Composable
fun LoginScreen(
    onLoginCompleted: (String, String) -> Unit
) {
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var profilePicBase64 by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val signika = FontFamily(
        Font(R.font.signika_negative_regular, weight = FontWeight.Normal)
    )

    val username_max_length = 20


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFe5d3e5))
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        // -------------------------
        // CARD FOTO PROFILO
        // -------------------------
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(32.dp))

                Text(
                    "FOTOGRAM",
                    style = MaterialTheme.typography.headlineMedium,
                    fontFamily = signika,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7d0885)
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    "Upload your profile picture",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = signika,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Medium
                )

                Spacer(Modifier.height(16.dp))

                Box(
                    modifier = Modifier.size(150.dp),
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

                Spacer(Modifier.height(8.dp))

                Text(
                    "Tap to change",
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = signika,
                    color = Color.Gray
                )
            }

            // textfield username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username", fontFamily = signika) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        tint = Color(0xFF7d0885)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF7d0885),
                    focusedLabelColor = Color(0xFF7d0885),
                    cursorColor = Color(0xFF7d0885)
                ),
                trailingIcon = {
                    InputCounter(username.length, username_max_length, modifier = Modifier)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )


            // messaggi di errore se non si inserisce username e/o foto profilo o il nome non é valido
            if (showError) {
                Spacer(Modifier.height(8.dp))

                val errorMessage = when {
                    username.length > username_max_length -> "Username must be less than $username_max_length characters"

                    username.isBlank() ->
                        "Add a username to sign up"

                    profilePicBase64.isBlank() ->
                        "Add a profile picture to sign up"

                    !isValidUsername(username) -> "Username can contain only letters, numbers and _"

                    else -> ""
                }

                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = signika,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(Modifier.height(24.dp))


            // pulsante di sign up
            Button(
                onClick = {
                    showError =
                        username.isBlank() || !isValidUsername(username) || profilePicBase64.isBlank() || username.length > username_max_length
                    if (!showError) {
                        onLoginCompleted(username, profilePicBase64)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7d0885),
                    disabledContainerColor = Color.Gray
                )
            ) {
                Text("Sign Up", fontFamily = signika, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(54.dp))
        }

    }
}

fun isValidUsername(username: String): Boolean {
    val regex = "^[A-Za-z0-9_]+$".toRegex()
    return username.matches(regex)
}


//@Composable
//fun LoginScreen(
//    onLoginCompleted: (String, String) -> Unit
//) {
//    val context = LocalContext.current
//    var username by remember { mutableStateOf("") }
//    var profilePicBase64 by remember { mutableStateOf(encodeResourceToBase64(context, R.drawable.user))}
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Text("FOTOGRAM!", style = MaterialTheme.typography.headlineMedium)
//
//        Spacer(Modifier.height(24.dp))
//
//        ImagePicker() { base64 ->
//            profilePicBase64 = base64
//        }
//
//        Spacer(Modifier.height(16.dp))
//
//        // Input text del nome utente
//        OutlinedTextField(
//            value = username,
//            onValueChange = { username = it },
//            label = { Text("Username") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(Modifier.height(24.dp))
//
//        // gestione della mancanza del nome utente
//        var showError by remember { mutableStateOf(false) }
//        Button(
//            onClick = {
//                showError = username.isBlank()
//                if (!showError) {
//                    onLoginCompleted(username, profilePicBase64)
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Sign Up")
//        }
//
//        if (showError) {
//            Spacer(Modifier.height(12.dp))
//            Text(
//                text = "Add a username to sign up",
//                color = MaterialTheme.colorScheme.error,
//                style = MaterialTheme.typography.bodyMedium
//            )
//        }
//    }
//}


//
//@Composable
//fun LoginScreen(onLoginClick: (String) -> Unit) {
//    var name by remember { mutableStateOf("") }
//    var base64Image by remember { mutableStateOf<String?>(null) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(32.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("WELCOME", fontSize = 40.sp)
//        Spacer(modifier = Modifier.height(24.dp))
//
//        ImagePicker(onImageSelected = { img -> base64Image = img })
//        Spacer(modifier = Modifier.height(24.dp))
//
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text("username") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Button(
//            onClick = { onLoginClick(name) },
//            shape = RoundedCornerShape(12.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp)
//        ) {
//            Text("LOGIN")
//        }
//    }
//}
//