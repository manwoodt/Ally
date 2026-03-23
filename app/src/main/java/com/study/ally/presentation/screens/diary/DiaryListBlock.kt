package com.study.ally.presentation.screens.diary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.study.ally.domain.model.DiaryEntry

@Composable
fun DiaryListBlock(
    entries: List<DiaryEntry>,
) {

    if (entries.isEmpty()) {
        Text(
            text = "Пока нет записей 👀\nДобавь первую!",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
        return
    }

    LazyColumn(
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(entries.reversed()) { entry ->
            DiaryItem(entry = entry)
        }
    }
}