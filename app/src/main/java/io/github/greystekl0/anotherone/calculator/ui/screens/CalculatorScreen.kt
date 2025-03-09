package io.github.greystekl0.anotherone.calculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.greystekl0.anotherone.calculator.domain.calculate
import io.github.greystekl0.anotherone.calculator.model.NumberRowConfig
import io.github.greystekl0.anotherone.calculator.ui.components.BottomButtonRow
import io.github.greystekl0.anotherone.calculator.ui.components.CalculatorDisplay
import io.github.greystekl0.anotherone.calculator.ui.components.NumberRow
import io.github.greystekl0.anotherone.calculator.ui.components.TopButtonRow

@Composable
fun Calculator(innerPadding: PaddingValues = PaddingValues()) {
    var example by remember { mutableStateOf("0") }
    var result by remember { mutableStateOf("0") }

    fun updateExampleAndCalculate(newExample: String) {
        example = newExample
        result = calculate(example)
    }

    Column(
        modifier =
            Modifier
                .padding(innerPadding)
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        CalculatorDisplay(example, result)
        TopButtonRow(example, ::updateExampleAndCalculate)
        NumberRow(example, ::updateExampleAndCalculate, NumberRowConfig("7", "8", "9", "*"))
        NumberRow(example, ::updateExampleAndCalculate, NumberRowConfig("4", "5", "6", "-"))
        NumberRow(example, ::updateExampleAndCalculate, NumberRowConfig("1", "2", "3", "+"))
        BottomButtonRow(example, result, ::updateExampleAndCalculate)
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorPreview() {
    Calculator()
}
