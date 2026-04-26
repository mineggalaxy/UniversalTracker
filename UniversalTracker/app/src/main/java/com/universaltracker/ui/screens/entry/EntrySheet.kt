package com.universaltracker.ui.screens.entry

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.universaltracker.data.local.model.TrackerFieldEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntrySheet(
    trackerId: String,
    entryId: String? = null,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    var notes by remember { mutableStateOf("") }
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (entryId == null) "Add Entry" else "Edit Entry",
                    style = MaterialTheme.typography.headlineSmall
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Dynamic fields will be rendered here based on tracker fields
            // For now, showing a placeholder
            Text(
                text = "Tracker Fields",
                style = MaterialTheme.typography.titleMedium
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = onSave,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Entry")
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun FieldInput(
    field: TrackerFieldEntity,
    value: String,
    onValueChange: (String) -> Unit
) {
    when (field.type) {
        com.universaltracker.data.local.model.FieldType.NUMBER -> {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text("${field.name}${if (field.unit != null) " (${field.unit})" else ""}") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        com.universaltracker.data.local.model.FieldType.DURATION -> {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text("${field.name} (mm:ss)") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        com.universaltracker.data.local.model.FieldType.TEXT -> {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text(field.name) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
