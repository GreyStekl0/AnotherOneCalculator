package com.github.greysteklo.anotherone.calculator.ui.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.greysteklo.anotherone.calculator.R
import com.github.greysteklo.anotherone.calculator.ui.calculator.CalculatorAction

data class GridButtonData(
    val content: ButtonContent,
    val action: CalculatorAction,
    val containerColor: Color? = null,
    val contentColor: Color? = null,
)

@Composable
private fun getCalculatorButtonLayout(): List<List<GridButtonData>> {
    val primaryContainerColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val secondaryContainerColor = MaterialTheme.colorScheme.secondaryContainer
    val onSecondaryContainerColor = MaterialTheme.colorScheme.onSecondaryContainer
    val tertiaryContainerColor = MaterialTheme.colorScheme.tertiaryContainer
    val onTertiaryContainerColor = MaterialTheme.colorScheme.onTertiaryContainer
    val errorContainerColor = MaterialTheme.colorScheme.errorContainer
    val onErrorContainerColor = MaterialTheme.colorScheme.onErrorContainer

    return listOf(
        listOf(
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.ac,
                    contentDescription = "AC",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Clear,
                containerColor = errorContainerColor,
                contentColor = onErrorContainerColor,
            ),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.staples,
                    contentDescription = "staples",
                    imageSize = 70.dp,
                ),
                CalculatorAction.Parentheses,
                containerColor = secondaryContainerColor,
                contentColor = onSecondaryContainerColor,
            ),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.percent,
                    contentDescription = "percent",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Percent,
                containerColor = secondaryContainerColor,
                contentColor = onSecondaryContainerColor,
            ),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.obelus,
                    contentDescription = "obelus",
                    imageSize = 150.dp,
                ),
                CalculatorAction.Operation("÷"),
                containerColor = tertiaryContainerColor,
                contentColor = onTertiaryContainerColor,
            ),
        ),
        listOf(
            GridButtonData(
                ButtonContent(text = "7"),
                CalculatorAction.Number(7),
            ),
            GridButtonData(ButtonContent(text = "8"), CalculatorAction.Number(8)),
            GridButtonData(ButtonContent(text = "9"), CalculatorAction.Number(9)),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.multiplication,
                    contentDescription = "multiplication",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Operation("×"),
                containerColor = tertiaryContainerColor,
                contentColor = onTertiaryContainerColor,
            ),
        ),
        listOf(
            GridButtonData(ButtonContent(text = "4"), CalculatorAction.Number(4)),
            GridButtonData(ButtonContent(text = "5"), CalculatorAction.Number(5)),
            GridButtonData(ButtonContent(text = "6"), CalculatorAction.Number(6)),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.minus,
                    contentDescription = "minus",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Operation("-"),
                containerColor = tertiaryContainerColor,
                contentColor = onTertiaryContainerColor,
            ),
        ),
        listOf(
            GridButtonData(ButtonContent(text = "1"), CalculatorAction.Number(1)),
            GridButtonData(ButtonContent(text = "2"), CalculatorAction.Number(2)),
            GridButtonData(ButtonContent(text = "3"), CalculatorAction.Number(3)),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.plus,
                    contentDescription = "plus",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Operation("+"),
                containerColor = tertiaryContainerColor,
                contentColor = onTertiaryContainerColor,
            ),
        ),
        listOf(
            GridButtonData(ButtonContent(text = "0"), CalculatorAction.Number(0)),
            GridButtonData(ButtonContent(text = ","), CalculatorAction.Decimal),
            GridButtonData(
                ButtonContent(iconResId = R.drawable.backspace, contentDescription = "Delete"),
                CalculatorAction.Delete,
                containerColor = secondaryContainerColor,
                contentColor = onSecondaryContainerColor,
            ),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.equal,
                    contentDescription = "equal",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Equally,
                containerColor = primaryContainerColor,
                contentColor = onPrimaryColor,
            ),
        ),
    )
}

typealias OnCalculatorAction = (CalculatorAction) -> Unit

@Composable
fun CalculatorButtonGrid(
    onAction: OnCalculatorAction,
    modifier: Modifier = Modifier,
) {
    val calculatorButtonLayout = getCalculatorButtonLayout()
    Column(modifier = modifier) {
        calculatorButtonLayout.forEach { rowButtons ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                rowButtons.forEach { buttonData ->
                    if (buttonData.containerColor != null && buttonData.contentColor != null) {
                        CalculatorButton(
                            onClick = { onAction(buttonData.action) },
                            content = buttonData.content,
                            containerColor = buttonData.containerColor,
                            contentColor = buttonData.contentColor,
                        )
                    } else {
                        CalculatorButton(
                            onClick = { onAction(buttonData.action) },
                            content = buttonData.content,
                        )
                    }
                }
            }
        }
    }
}
