package com.example.validation1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.validation1.ui.theme.Validation1Theme

class OTPValidation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Validation1Theme {
                OTPValidationScreen(
                    onNavigateToResetPassword = {
                        val intent = Intent(this@OTPValidation, ResetPassword::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
    
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        // Navigation vers la page ForgotPassword
        val intent = Intent(this@OTPValidation, ForgotPassword::class.java)
        startActivity(intent)
        finish()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPValidationScreen(onNavigateToResetPassword: () -> Unit = {}) {
    var otp1 by remember { mutableStateOf("") }
    var otp2 by remember { mutableStateOf("") }
    var otp3 by remember { mutableStateOf("") }
    var otp4 by remember { mutableStateOf("") }

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
                text = "Enter the code sent to you by",
                color = colorResource(id = R.color.black),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()

            )
            Text(
                text = "Email or Mobile number",
                color = colorResource(id = R.color.black),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(60.dp))

            // OTP Input Fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OTPBox(value = otp1, onValueChange = { if (it.length <= 1) otp1 = it })
                OTPBox(value = otp2, onValueChange = { if (it.length <= 1) otp2 = it })
                OTPBox(value = otp3, onValueChange = { if (it.length <= 1) otp3 = it })
                OTPBox(value = otp4, onValueChange = { if (it.length <= 1) otp4 = it })
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Verify Button
            Button(
                onClick = { onNavigateToResetPassword() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.gamer_pink)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Verify",
                    color = colorResource(id = R.color.white),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Didn't receive text
            Text(
                text = "Didn't receive a verification code?",
                color = colorResource(id = R.color.black),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Resend code
            Text(
                text = "Resend code",
                color = colorResource(id = R.color.gamer_pink),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { /* Handle resend */ }

            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun OTPBox(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .width(70.dp)
            .height(70.dp),
        textStyle = LocalTextStyle.current.copy(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.gamer_pink),
            textAlign = TextAlign.Start
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.gamer_pink),
            unfocusedBorderColor = colorResource(id = R.color.border_gray)
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun OTPValidationScreenPreview() {
    Validation1Theme {
        OTPValidationScreen()
    }
}