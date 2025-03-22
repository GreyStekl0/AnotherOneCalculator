package com.github.greysteklo.anotherone.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.greysteklo.anotherone.calculator.ui.theme.AnotherOneCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnotherOneCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculator(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Calculator(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "2+2")
        Text(text = "4")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "AC")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "()")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "%")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "/")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "7")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "8")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "9")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "X")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "4")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "5")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "6")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "-")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "1")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "2")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "3")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "+")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "0")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = ",")
            }
            Button(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(R.drawable.backspace),
                    contentDescription = "equals",
                )
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "=")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCalculator() {
    AnotherOneCalculatorTheme {
        Calculator(Modifier)
    }
}
