package com.example.validation1

import android.content.Intent
import android.os.Bundle
import androidx.compose.ui.text.style.TextAlign
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.validation1.ui.theme.Validation1Theme

class ForgotPassword : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Validation1Theme {
                ForgotPasswordScreen(
                    onNavigateToOTP = {
                        val intent = Intent(this@ForgotPassword, OTPValidation::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
    
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        // Navigation vers la page de connexion
        val intent = Intent(this@ForgotPassword, login::class.java)
        startActivity(intent)
        finish()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(onNavigateToOTP: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = colorResource(id = R.color.gamer_pink)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.white)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Title
            Text(
                text = "Forgot Password",
                color = colorResource(id = R.color.black),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()      // ✅ occuper toute la largeur dispo
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle
            Text(
                text = "Please enter your registered email to reset your password",
                color = colorResource(id = R.color.gamer_pink),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = colorResource(id = R.color.gamer_pink)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.border_gray),
                    unfocusedBorderColor = colorResource(id = R.color.gamer_pink),
                    focusedLabelColor = colorResource(id = R.color.gamer_pink)
                ),

                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button
            Button(
                onClick = { /* Handle submit */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.gamer_pink)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Submit",
                    color = colorResource(id = R.color.white),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // OR text
            Text(
                text = "OR",
                color = colorResource(id = R.color.gamer_pink),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Send SMS Button
            Button(
                onClick = { onNavigateToOTP() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.gamer_pink)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Send SMS",
                    color = colorResource(id = R.color.white),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    Validation1Theme {
        ForgotPasswordScreen()
    }
}