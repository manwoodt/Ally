package com.study.ally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.study.ally.presentation.navigation.AppNavHost
import com.study.ally.ui.theme.AllyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AllyTheme {
                AppNavHost()
            }
        }
    }
}
