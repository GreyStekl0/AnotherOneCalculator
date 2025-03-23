package com.github.greysteklo.anotherone.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val example by remember { mutableStateOf("2+2") }
    val result by remember { mutableStateOf("4") }
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = example,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color = Color.Black,
            fontSize = 80.sp,
        )
        Text(
            text = "= $result",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color = Color.Gray,
            fontSize = 40.sp,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "AC", fontSize = 30.sp, softWrap = false)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "( )", fontSize = 40.sp, softWrap = false)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "%", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "/", fontSize = 45.sp)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "7", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "8", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "9", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "X", fontSize = 45.sp)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "4", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "5", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "6", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "-", fontSize = 45.sp)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "1", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "2", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "3", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "+", fontSize = 45.sp)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "0", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = ",", fontSize = 45.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.backspace),
                    contentDescription = "equals",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(45.dp),
                )
            }
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "=", fontSize = 45.sp)
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
