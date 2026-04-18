package com.universaltracker.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "trackers")
data class TrackerEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val type: TrackerType,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val emoji: String = "",
    val themeToken: String = "",
    val tags: String = "", // Stored as JSON array
    val isPinned: Boolean = false,
    val pinOrder: Int = 0,
    val archived: Boolean = false
)

enum class TrackerType {
    STRUCTURED,
    SIMPLE
}
