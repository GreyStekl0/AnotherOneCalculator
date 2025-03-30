package com.github.greysteklo.anotherone.calculator.ui.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorDisplay(
    expression: String,
    result: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Text(
            text = expression,
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
    }
}
