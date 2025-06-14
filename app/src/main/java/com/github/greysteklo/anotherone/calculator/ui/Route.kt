package com.github.greysteklo.anotherone.calculator.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.github.greysteklo.anotherone.calculator.ui.calculator.CalculatorScreen
import com.github.greysteklo.anotherone.calculator.ui.history.HistoryScreen
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

enum class DragAnchors {
    Collapsed,
    Expanded,
}

@Composable
fun CalculatorWithHistory(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var historyViewHeight by remember { mutableFloatStateOf(0f) }

    val state =
        remember {
            AnchoredDraggableState(
                initialValue = DragAnchors.Collapsed,
                anchors = DraggableAnchors { DragAnchors.Collapsed at 0f },
            )
        }

    LaunchedEffect(historyViewHeight) {
        if (historyViewHeight > 0f) {
            val newAnchors =
                DraggableAnchors {
                    DragAnchors.Collapsed at 0f
                    DragAnchors.Expanded at historyViewHeight
                }
            state.updateAnchors(newAnchors)
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        HistoryScreen(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .onSizeChanged { size ->
                        historyViewHeight = size.height.toFloat()
                    },
        )

        CalculatorScreen(
            modifier =
                Modifier
                    .fillMaxSize()
                    .offset { IntOffset(x = 0, y = state.requireOffset().roundToInt()) }
                    .anchoredDraggable(
                        state = state,
                        orientation = Orientation.Vertical,
                        enabled = state.currentValue == DragAnchors.Collapsed,
                    ),
        )

        if (state.currentValue == DragAnchors.Expanded) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.1f)
                        .align(Alignment.BottomCenter)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                scope.launch {
                                    state.animateTo(DragAnchors.Collapsed)
                                }
                            },
                        ),
            ) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowUp,
                    contentDescription = "Tap to close history",
                    tint = Color.Gray,
                    modifier = Modifier.size(48.dp).align(Alignment.Center),
                )
            }
        }
    }
}
