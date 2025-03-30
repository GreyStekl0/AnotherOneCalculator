package com.github.greysteklo.anotherone.calculator.ui.calculator.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.CalculatorButton(
    onClick: () -> Unit,
    content: ButtonContent,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier =
            modifier
                .weight(1f)
                .aspectRatio(1f)
                .padding(2.dp),
    ) {
        if (content.text != null) {
            Text(
                text = content.text,
                fontSize = content.fontSize,
                softWrap = false,
            )
        } else if (content.iconResId != null) {
            Image(
                painter = painterResource(content.iconResId),
                contentDescription = content.contentDescription,
                colorFilter = ColorFilter.tint(ButtonDefaults.buttonColors().contentColor),
                modifier = Modifier.size(45.dp),
            )
        }
    }
}
