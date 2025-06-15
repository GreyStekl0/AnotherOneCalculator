package com.github.greysteklo.anotherone.calculator.ui

import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.greysteklo.anotherone.calculator.ui.calculator.CalculatorAction
import com.github.greysteklo.anotherone.calculator.ui.calculator.CalculatorScreen
import com.github.greysteklo.anotherone.calculator.ui.calculator.CalculatorViewModel
import com.github.greysteklo.anotherone.calculator.ui.calculator.UiEvent
import com.github.greysteklo.anotherone.calculator.ui.history.HistoryScreen
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

enum class DragAnchors {
    Collapsed,
    Expanded,
}

@Composable
fun CalculatorWithHistory(
    modifier: Modifier = Modifier,
    calculatorViewModel: CalculatorViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    var historyViewHeight by remember { mutableFloatStateOf(0f) }

    val state =
        remember {
            AnchoredDraggableState(
                initialValue = DragAnchors.Collapsed,
            )
        }

    LaunchedEffect(Unit) {
        calculatorViewModel.uiEvents.collect { event ->
            when (event) {
                is UiEvent.CollapseHistory -> {
                    scope.launch {
                        state.animateTo(DragAnchors.Collapsed)
                    }
                }
            }
        }
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
                    .fillMaxHeight()
                    .onSizeChanged { size ->
                        historyViewHeight = size.height.toFloat()
                    },
            isExpanded = state.targetValue == DragAnchors.Expanded,
            onClose = {
                scope.launch {
                    state.animateTo(DragAnchors.Collapsed)
                }
            },
            onHistoryItemClick = { calculation ->
                calculatorViewModel.onAction(CalculatorAction.LoadFromHistory(calculation))
            },
        )

        CalculatorScreen(
            modifier =
                Modifier
                    .fillMaxSize()
                    .offset {
                        val yOffset = state.offset.takeUnless { it.isNaN() } ?: 0f
                        IntOffset(x = 0, y = yOffset.roundToInt())
                    }.anchoredDraggable(
                        state = state,
                        orientation = Orientation.Vertical,
                    ),
        )
    }
}
