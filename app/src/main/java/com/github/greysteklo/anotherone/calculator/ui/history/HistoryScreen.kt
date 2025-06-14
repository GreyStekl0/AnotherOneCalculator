package com.github.greysteklo.anotherone.calculator.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val historyList by viewModel.history.collectAsState()

    // ИЗМЕНЕНО: Мы больше не добавляем .fillMaxSize() здесь.
    // Мы просто используем тот modifier, который пришел снаружи.
    val commonModifier =
        modifier
            .background(MaterialTheme.colorScheme.background)

    if (historyList.isEmpty()) {
        Box(
            modifier = commonModifier,
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "History is empty")
        }
    } else {
        LazyColumn(
            modifier = commonModifier,
            reverseLayout = true,
        ) {
            items(historyList) { calculation ->
                Text(
                    text = "${calculation.expression} = ${calculation.result}",
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(end = 18.dp, start = 18.dp),
                    // Добавил отступ слева для симметрии
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
