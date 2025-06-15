package com.github.greysteklo.anotherone.calculator.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.greysteklo.anotherone.calculator.domain.model.Calculation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onClose: () -> Unit,
    onHistoryItemClick: (Calculation) -> Unit,
    isExpanded: Boolean,
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val historyList by viewModel.history.collectAsState()

    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            if (historyList.isNotEmpty()) {
                CenterAlignedTopAppBar(
                    title = { Text("History") },
                    actions = {
                        IconButton(onClick = { viewModel.onClearHistory() }) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "Clear history",
                            )
                        }
                    },
                )
            }
        },
        bottomBar = {
            if (isExpanded) {
                BottomAppBar(
                    modifier = Modifier.height(56.dp),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.Gray,
                ) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = onClose,
                                ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowUp,
                            contentDescription = "Tap to close history",
                            modifier = Modifier.size(48.dp),
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        if (historyList.isEmpty()) {
            Box(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "History is empty")
            }
        } else {
            LazyColumn(
                modifier =
                    Modifier
                        .padding(innerPadding),
                reverseLayout = true,
            ) {
                items(historyList) { calculation ->
                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(color = Color.Gray)) {
                                append("${calculation.expression}= ")
                            }
                            withStyle(SpanStyle()) {
                                append(calculation.result)
                            }
                        },
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = { onHistoryItemClick(calculation) },
                                ).padding(end = 18.dp, start = 18.dp),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 40.sp,
                        lineHeight = 40.sp * 1.2,
                        softWrap = false,
                    )
                }
            }
        }
    }
}
