package com.universaltracker.ui.screens.timeline

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.universaltracker.data.local.model.EntryEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(
    trackerId: String,
    onEntryClick: (String) -> Unit
) {
    var selectedTimeRange by remember { mutableStateOf("ALL") }
    var entries by remember { mutableStateOf<List<EntryEntity>>(emptyList()) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Timeline") },
                actions = {
                    // Time range filter
                    FilterChip(
                        selected = true,
                        onClick = { selectedTimeRange = "1W" },
                        label = { Text("1W") }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    FilterChip(
                        selected = false,
                        onClick = { selectedTimeRange = "1M" },
                        label = { Text("1M") }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    FilterChip(
                        selected = false,
                        onClick = { selectedTimeRange = "ALL" },
                        label = { Text("ALL") }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Chart section - placeholder for now
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text("Chart Visualization Here")
                }
            }
            
            // Statistics section
            StatisticsSection()
            
            // Entries list
            Text(
                text = "Entries",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
            
            if (entries.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text("No entries yet")
                }
            }
        }
    }
}

@Composable
fun StatisticsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Statistics",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(label = "Min", value = "-")
                StatItem(label = "Max", value = "-")
                StatItem(label = "Mean", value = "-")
                StatItem(label = "Latest", value = "-")
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
