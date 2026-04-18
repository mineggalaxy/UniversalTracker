package com.universaltracker.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "reminders",
    foreignKeys = [
        ForeignKey(
            entity = TrackerEntity::class,
            parentColumns = ["id"],
            childColumns = ["trackerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["trackerId"])]
)
data class ReminderEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val trackerId: String,
    val timeHour: Int,
    val timeMinute: Int,
    val isEnabled: Boolean = true,
    val daysOfWeek: String = "" // Stored as JSON array of Int (0-6, where 0 is Sunday)
)
