package ru.dima.moviesapp.ui.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Избранное") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Здесь будет список избранных фильмов", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
