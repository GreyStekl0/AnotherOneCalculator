package com.github.greysteklo.anotherone.calculator.ui.calculator.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.CalculatorButton(
    onClick: () -> Unit,
    content: ButtonContent,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val cornerRadiusPercent by animateFloatAsState(
        targetValue = if (isPressed) 20f else 50f,
        animationSpec = tween(durationMillis = 160, easing = LinearEasing),
        label = "cornerRadius",
    )
    val haptic = LocalHapticFeedback.current
    Button(
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
            onClick()
        },
        modifier =
            modifier
                .weight(1f)
                .aspectRatio(1f)
                .padding(4.dp),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
        interactionSource = interactionSource,
        shape = RoundedCornerShape(cornerRadiusPercent.dp),
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
                colorFilter = ColorFilter.tint(contentColor),
                modifier = Modifier.size(content.imageSize),
            )
        }
    }
}
