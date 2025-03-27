package com.github.greysteklo.anotherone.calculator.ui.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.greysteklo.anotherone.calculator.R
import com.github.greysteklo.anotherone.calculator.ui.theme.AnotherOneCalculatorTheme
import com.github.greysteklo.anotherone.calculator.viewmodel.CalculatorAction
import com.github.greysteklo.anotherone.calculator.viewmodel.CalculatorViewModel

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = state.expression,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color = Color.Black,
            fontSize = 80.sp,
        )
        Text(
            text = "= ${state.result}",
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
                onClick = { viewModel.onAction(CalculatorAction.Clear) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "AC", fontSize = 30.sp, softWrap = false)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Parentheses) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "( )", fontSize = 40.sp, softWrap = false)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Percent) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "%", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Operation("/")) },
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
                onClick = { viewModel.onAction(CalculatorAction.Number(7)) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "7", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Number(8)) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "8", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Number(9)) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "9", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Operation("*")) },
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
                onClick = { viewModel.onAction(CalculatorAction.Number(4)) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "4", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Number(5)) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "5", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Number(6)) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "6", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Operation("-")) },
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
                onClick = { viewModel.onAction(CalculatorAction.Number(1)) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "1", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Number(2)) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "2", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Number(3)) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "3", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Operation("+")) },
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
                onClick = { viewModel.onAction(CalculatorAction.Number(0)) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = "0", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Decimal) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Text(text = ",", fontSize = 45.sp)
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Delete) },
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(2.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.backspace),
                    contentDescription = "Delete",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(45.dp),
                )
            }
            Button(
                onClick = { viewModel.onAction(CalculatorAction.Calculate) },
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
private fun PreviewCalculatorScreen() {
    AnotherOneCalculatorTheme {
        CalculatorScreen()
    }
}
