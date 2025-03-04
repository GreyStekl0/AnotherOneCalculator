package io.github.greystekl0.anotherone.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.github.greystekl0.anotherone.calculator.ui.theme.AnotherOneCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnotherOneCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculator(innerPadding)
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
private fun Calculator(innerPadding: PaddingValues = PaddingValues()) {
    var example by
        remember {
            mutableStateOf("3+2")
        }
    val result by
        remember {
            mutableIntStateOf(0)
        }
    Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
        Text(text = example, fontSize = 30.sp)
        Text(text = result.toString(), fontSize = 30.sp)
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { example = "" }) {
                Text("AC")
            }
            Button(onClick = { }) {
                Image(
                    painter = painterResource(R.drawable.backspace),
                    contentDescription = "backspace",
                )
            }
            Button(onClick = { }) {
                Text("%")
            }
            Button(onClick = { }) {
                Text("/")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { }) {
                Text("7")
            }
            Button(onClick = { }) {
                Text("8")
            }
            Button(onClick = { }) {
                Text("9")
            }
            Button(onClick = { }) {
                Text("*")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { }) {
                Text("4")
            }
            Button(onClick = { }) {
                Text("5")
            }
            Button(onClick = { }) {
                Text("6")
            }
            Button(onClick = { }) {
                Text("-")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { }) {
                Text("1")
            }
            Button(onClick = { }) {
                Text("2")
            }
            Button(onClick = { }) {
                Text("3")
            }
            Button(onClick = { }) {
                Text("+")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { }) {
                Text("?")
            }
            Button(onClick = { }) {
                Text("0")
            }
            Button(onClick = { }) {
                Text(",")
            }
            Button(onClick = { }) {
                Text("=")
            }
        }
    }
}
