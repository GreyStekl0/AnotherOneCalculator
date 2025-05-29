package com.github.greysteklo.anotherone.calculator.ui.calculator.components

import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorDisplay(
    expression: String,
    result: String,
    modifier: Modifier = Modifier,
) {
    val scrollStateExpression = rememberScrollState()
    val scrollStateResult = rememberScrollState()
    val expressionBaseSize = 80
    val expressionFontSize =
        if (expression.length <= 7) {
            expressionBaseSize.sp
        } else {
            val newSize = expressionBaseSize - (expression.length - 7) * 7
            maxOf(40, newSize).sp
        }
    val resultBaseSize = 40
    val resultFontSize =
        if (expression.length <= 7) {
            resultBaseSize.sp
        } else {
            val newSize = resultBaseSize - (expression.length - 7) * 7
            maxOf(20, newSize).sp
        }
    LaunchedEffect(expression) {
        scrollStateExpression.scrollTo(scrollStateExpression.maxValue)
    }

    Column(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
        CompositionLocalProvider(
            LocalOverscrollFactory provides null,
        ) {
            Text(
                text = expression,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollStateExpression),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = expressionFontSize,
                lineHeight = expressionFontSize * 1.2,
                softWrap = false,
            )
        }
        Text(
            text = "= $result",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollStateResult),
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = resultFontSize,
            softWrap = false,
        )
    }
}
