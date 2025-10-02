package com.example.validation1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.validation1.ui.theme.Validation1Theme

class login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.initialize(this)
        enableEdgeToEdge()
        setContent {
            Validation1Theme {
                LoginScreen(
                    onNavigateToSignup = {
                        val intent = Intent(this@login, signup::class.java)
                        startActivity(intent)
                    },
                    onNavigateToForgotPassword = {
                        val intent = Intent(this@login, ForgotPassword::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    onNavigateToSignup: () -> Unit = {},
    onNavigateToForgotPassword: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val isDarkMode by ThemeManager.isDarkModeState()

    // Couleurs dynamiques selon le thème
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val secondaryTextColor = if (isDarkMode) Color.White else colorResource(id = R.color.gamer_pink)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Bouton Dark Mode en haut à droite
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { ThemeManager.toggleTheme(context) },
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = if (isDarkMode) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                if (isDarkMode) {
                    // Icône Soleil pour mode light
                    SunIcon(tint = Color.White)
                } else {
                    // Icône Lune pour mode dark
                    MoonIcon(tint = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Logo de l'app
        AppLogo()

        Spacer(modifier = Modifier.height(20.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = secondaryTextColor) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = colorResource(id = R.color.gamer_pink)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.gamer_pink),
                unfocusedBorderColor = if (isDarkMode) Color.White.copy(alpha = 0.5f) else colorResource(id = R.color.border_gray),
                focusedLabelColor = colorResource(id = R.color.gamer_pink),
                unfocusedTextColor = textColor,
                focusedTextColor = textColor
            ),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = secondaryTextColor) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password",
                    tint = colorResource(id = R.color.gamer_pink)
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    if (passwordVisible) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Hide password",
                            tint = colorResource(id = R.color.gamer_pink)
                        )
                    } else {
                        EyeIcon(
                            tint = colorResource(id = R.color.gamer_pink)
                        )
                    }
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.gamer_pink),
                unfocusedBorderColor = if (isDarkMode) Color.White.copy(alpha = 0.5f) else colorResource(id = R.color.border_gray),
                focusedLabelColor = colorResource(id = R.color.gamer_pink),
                unfocusedTextColor = textColor,
                focusedTextColor = textColor
            ),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Remember Me and Forgot Password
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.gamer_red),
                        uncheckedColor = colorResource(id = R.color.gamer_pink)
                    )
                )
                Text(
                    text = "Remember Me",
                    color = textColor,
                    fontSize = 14.sp
                )
            }

            Text(
                text = "Forgot password ?",
                color = colorResource(id = R.color.gamer_pink),
                fontSize = 14.sp,
                modifier = Modifier.clickable { onNavigateToForgotPassword() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        Button(
            onClick = { /* Handle login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.gamer_pink)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "LOGIN",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // OR text
        Text(
            text = "OR",
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Social Login Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Facebook Button
            Button(
                onClick = { /* Handle Facebook login */ },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.facebook_blue)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "f",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Facebook",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }

            // Google Button
            OutlinedButton(
                onClick = { /* Handle Google login */ },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isDarkMode) Color.White.copy(alpha = 0.5f) else colorResource(id = R.color.border_gray)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "G",
                        color = Color(0xFF4285F4),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Google",
                        color = textColor,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Register text
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account? ",
                color = textColor,
                fontSize = 14.sp
            )
            Text(
                text = "Register Now",
                color = colorResource(id = R.color.gamer_pink),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onNavigateToSignup() }
            )
        }
    }
}

@Composable
fun AppLogo() {
    Image(
        painter = painterResource(id = R.mipmap.logo_foreground),
        contentDescription = "App Logo",
        modifier = Modifier
            .size(280.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun EyeIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier.size(24.dp)
    ) {
        val strokeWidth = 2.dp.toPx()
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = minOf(size.width, size.height) * 0.3f

        drawCircle(
            color = tint,
            radius = radius,
            center = Offset(centerX, centerY),
            style = Stroke(width = strokeWidth)
        )

        drawCircle(
            color = tint,
            radius = radius * 0.4f,
            center = Offset(centerX, centerY)
        )
    }
}

@Composable
fun MoonIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier.size(24.dp)
    ) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = minOf(size.width, size.height) * 0.35f

        // Croissant de lune
        drawCircle(
            color = tint,
            radius = radius,
            center = Offset(centerX - radius * 0.2f, centerY)
        )

        drawCircle(
            color = Color.Transparent,
            radius = radius * 0.8f,
            center = Offset(centerX + radius * 0.3f, centerY - radius * 0.1f),
            blendMode = androidx.compose.ui.graphics.BlendMode.Clear
        )
    }
}

@Composable
fun SunIcon(
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier.size(24.dp)
    ) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = minOf(size.width, size.height) * 0.25f
        val rayLength = radius * 0.6f

        // Cercle central (soleil)
        drawCircle(
            color = tint,
            radius = radius,
            center = Offset(centerX, centerY)
        )

        // Rayons du soleil (8 rayons)
        for (i in 0..7) {
            val angle = (i * 45f) * (Math.PI / 180f).toFloat()
            val startX = centerX + kotlin.math.cos(angle) * (radius + 2.dp.toPx())
            val startY = centerY + kotlin.math.sin(angle) * (radius + 2.dp.toPx())
            val endX = centerX + kotlin.math.cos(angle) * (radius + rayLength + 2.dp.toPx())
            val endY = centerY + kotlin.math.sin(angle) * (radius + rayLength + 2.dp.toPx())

            drawLine(
                color = tint,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = 2.dp.toPx()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Validation1Theme {
        LoginScreen(
            onNavigateToSignup = {},
            onNavigateToForgotPassword = {}
        )
    }
}