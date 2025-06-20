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
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.greysteklo.anotherone.calculator.R
import com.github.greysteklo.anotherone.calculator.domain.model.SavedCalculation
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onClose: () -> Unit,
    onHistoryItemClick: (SavedCalculation) -> Unit,
    isExpanded: Boolean,
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val historyList by viewModel.history.collectAsStateWithLifecycle()
    val jokeState by viewModel.uiState.collectAsStateWithLifecycle()
    var jokeIsVisible by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            if (historyList.isNotEmpty()) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            stringResource(id = R.string.history_name),
                            modifier =
                                Modifier.clickable {
                                    jokeIsVisible = true
                                    viewModel.getJoke()
                                },
                        )
                    },
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
        if (jokeIsVisible) {
            BasicAlertDialog(
                onDismissRequest = { jokeIsVisible = false },
                modifier =
                    Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainerHigh,
                            shape = MaterialTheme.shapes.extraLarge,
                        ).padding(24.dp),
                content = {
                    Text(jokeState.joke.text)
                },
            )
        }
        if (historyList.isEmpty()) {
            Box(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.history_is_empty),
                    modifier =
                        Modifier.clickable {
                            jokeIsVisible = true
                            viewModel.getJoke()
                        },
                )
            }
        } else {
            val sortedDates = historyList.keys.sortedDescending()

            LazyColumn(
                modifier =
                    Modifier
                        .padding(innerPadding),
            ) {
                sortedDates.forEachIndexed { index, date ->
                    val calculationsForDate = historyList[date].orEmpty()

                    stickyHeader {
                        DateHeader(date = date)
                    }
                    items(calculationsForDate) { calculation ->
                        Text(
                            buildAnnotatedString {
                                withStyle(SpanStyle(color = Color.DarkGray)) {
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
                            fontSize = 40.sp,
                            lineHeight = 40.sp * 1.2,
                            softWrap = false,
                        )
                    }
                    if (index < sortedDates.lastIndex) {
                        item {
                            HorizontalDivider(
                                modifier =
                                    Modifier
                                        .padding(horizontal = 8.dp, vertical = 8.dp),
                                thickness = 1.dp,
                                color = Color.LightGray,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DateHeader(
    date: LocalDate,
    modifier: Modifier = Modifier,
) {
    val headerText =
        when {
            date.isEqual(LocalDate.now()) -> stringResource(id = R.string.history_header_today)
            date.isEqual(
                LocalDate.now().minusDays(1),
            ) -> stringResource(id = R.string.history_header_yesterday)

            else -> date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
        }

    Text(
        text = headerText,
        textAlign = TextAlign.End,
        fontSize = 28.sp,
        lineHeight = 28.sp * 1.2,
        color = Color.Gray,
        modifier =
            modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
    )
}
