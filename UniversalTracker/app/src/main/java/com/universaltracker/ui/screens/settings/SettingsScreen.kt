package com.universaltracker.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    var darkTheme by remember { mutableStateOf(false) }
    var missedTrackerDays by remember { mutableStateOf("3") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // General Section
            SettingsSection(title = "General") {
                SettingsSwitchItem(
                    title = "Dark Theme",
                    subtitle = "Use dark theme",
                    checked = darkTheme,
                    onCheckedChange = { darkTheme = it }
                )
            }
            
            // Data Section
            SettingsSection(title = "Data") {
                SettingsButtonItem(
                    title = "Export Data",
                    subtitle = "Export all data as JSON",
                    onClick = { /* Export */ }
                )
                SettingsButtonItem(
                    title = "Import Data",
                    subtitle = "Import data from JSON",
                    onClick = { /* Import */ }
                )
                SettingsButtonItem(
                    title = "Clear All Data",
                    subtitle = "Delete all trackers and entries",
                    onClick = { /* Clear */ }
                )
            }
            
            // Behavior Section
            SettingsSection(title = "Behavior") {
                OutlinedTextField(
                    value = missedTrackerDays,
                    onValueChange = { missedTrackerDays = it },
                    label = { Text("Missed Tracker Duration (days)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        content()
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
fun SettingsSwitchItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun SettingsButtonItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = { Text(subtitle) },
        modifier = Modifier.fillMaxWidth()
    )
}
