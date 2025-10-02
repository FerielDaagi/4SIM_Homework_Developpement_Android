package com.example.validation1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.validation1.ui.theme.Validation1Theme
import kotlinx.coroutines.delay

class Splash_screen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Validation1Theme {
                SplashScreen {
                    // Redirection vers la page de connexion après 3 secondes
                    val intent = Intent(this@Splash_screen, login::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}

@Composable
fun SplashScreen(onNavigateToLogin: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000) // Attendre 3 secondes
        onNavigateToLogin()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo de l'app (icône de lancement)
            SplashAppLogo()
            
            Spacer(modifier = Modifier.height(40.dp))
            

        }
    }
}

@Composable
fun SplashAppLogo() {
    // Utilisation de l'icône de l'app (logo_foreground) pour le splash screen
    Image(
        painter = painterResource(id = R.mipmap.logo_foreground),
        contentDescription = "App Logo",
        modifier = Modifier
            .size(400.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentScale = ContentScale.Fit
    )
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Validation1Theme {
        SplashScreen(onNavigateToLogin = {})
    }
}