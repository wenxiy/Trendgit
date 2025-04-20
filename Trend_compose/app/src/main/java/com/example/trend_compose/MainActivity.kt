package com.example.trend_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.trend_compose.ui.theme.MainScreen
import com.example.trend_compose.ui.theme.Trend_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Trend_ComposeTheme {
                MainScreen()
            }
        }
    }
}
