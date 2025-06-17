package com.github.greysteklo.anotherone.calculator.ui.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.greysteklo.anotherone.calculator.ui.calculator.components.CalculatorButtonGrid
import com.github.greysteklo.anotherone.calculator.ui.calculator.components.CalculatorDisplay
import com.github.greysteklo.anotherone.calculator.ui.theme.AnotherOneCalculatorTheme

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier =
            modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CalculatorDisplay(
            expression = state.expression,
            result = state.result,
            modifier =
                Modifier
                    .weight(1f)
                    .padding(bottom = 16.dp),
        )

        CalculatorButtonGrid(
            onAction = viewModel::onAction,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorScreenPreview() {
    AnotherOneCalculatorTheme {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CalculatorDisplay(
                expression = "123+45",
                result = "168",
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(bottom = 16.dp),
            )
            CalculatorButtonGrid(onAction = {})
            Spacer(modifier = Modifier.padding(2.dp))
        }
    }
}
