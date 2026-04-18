package com.universaltracker.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.universaltracker.data.local.model.TrackerEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    var trackers by remember { mutableStateOf<List<TrackerEntity>>(emptyList()) }
    
    // TODO: Load trackers from database using ViewModel
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Universal Tracker") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Navigate to create tracker */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Tracker")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Missed Trackers Section
            if (trackers.isNotEmpty()) {
                item {
                    SectionHeader(title = "Missed")
                }
                items(trackers.take(3)) { tracker ->
                    TrackerCard(tracker = tracker)
                }
            }
            
            // Pinned Trackers Section
            val pinnedTrackers = trackers.filter { it.isPinned }
            if (pinnedTrackers.isNotEmpty()) {
                item {
                    SectionHeader(title = "Pinned")
                }
                items(pinnedTrackers) { tracker ->
                    TrackerCard(tracker = tracker)
                }
            }
            
            // All Trackers Section
            item {
                SectionHeader(title = "All Trackers")
            }
            if (trackers.isEmpty()) {
                item {
                    EmptyState()
                }
            } else {
                items(trackers) { tracker ->
                    TrackerCard(tracker = tracker)
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun TrackerCard(tracker: TrackerEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { /* Navigate to entry sheet */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tracker.emoji.ifEmpty { "📊" },
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(end = 12.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = tracker.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Last logged: Never",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "📊",
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No trackers yet",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tap the + button to create your first tracker",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
