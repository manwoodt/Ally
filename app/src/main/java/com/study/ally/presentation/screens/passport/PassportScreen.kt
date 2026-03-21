package com.study.ally.presentation.screens.passport

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun PassportScreen(
    viewModel: PassportViewModel = koinViewModel()
) {

    val allergens by viewModel.state.collectAsState()

    LazyColumn {
        items(allergens) {
            AllergenItem(it)
        }
    }
}