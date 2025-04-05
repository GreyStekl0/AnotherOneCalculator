package com.github.greysteklo.anotherone.calculator.ui.calculator.components

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ButtonContent(
    val text: String? = null,
    val iconResId: Int? = null,
    val contentDescription: String? = if (text == null && iconResId != null) "Calculator Icon Button" else text,
    val fontSize: TextUnit = 45.sp,
    val imageSize: Dp = 45.dp,
) {
    init {
        require(text == null || iconResId == null) {
            "ButtonContent can have either text or iconResId, not both."
        }
        require(text != null || iconResId != null) {
            "ButtonContent must have either text or iconResId."
        }
    }
}
