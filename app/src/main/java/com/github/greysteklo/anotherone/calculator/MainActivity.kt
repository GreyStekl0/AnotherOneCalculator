package com.github.greysteklo.anotherone.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.github.greysteklo.anotherone.calculator.ui.CalculatorWithHistory
import com.github.greysteklo.anotherone.calculator.ui.theme.AnotherOneCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnotherOneCalculatorTheme {
                CalculatorWithHistory(Modifier.fillMaxSize())
            }
        }
    }
}
