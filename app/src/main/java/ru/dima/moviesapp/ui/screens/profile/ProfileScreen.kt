package ru.dima.moviesapp.ui.screens.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Profile") }) }
    ) { padding ->
        Text(
            "User profile information will go here.",
            modifier = androidx.compose.ui.Modifier.padding(padding).padding(16.dp)
        )
    }
}