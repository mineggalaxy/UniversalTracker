package com.universaltracker.ui.screens.trackers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.universaltracker.data.local.model.TrackerEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackersScreen(
    onTrackerClick: (String) -> Unit,
    onCreateTracker: () -> Unit
) {
    var trackers by remember { mutableStateOf<List<TrackerEntity>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trackers") },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateTracker,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(trackers) { tracker ->
                TrackerListItem(
                    tracker = tracker,
                    onClick = { onTrackerClick(tracker.id) }
                )
            }
        }
    }
}

@Composable
fun TrackerListItem(
    tracker: TrackerEntity,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "${tracker.emoji.ifEmpty { "📊" }} ${tracker.name}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${tracker.type}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
