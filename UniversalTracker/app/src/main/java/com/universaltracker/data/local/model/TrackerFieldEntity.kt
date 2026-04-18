package com.universaltracker.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "tracker_fields",
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
data class TrackerFieldEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val trackerId: String,
    val name: String,
    val type: FieldType,
    val required: Boolean = false,
    val unit: String? = null,
    val allowsNumeric: Boolean = false, // For TEXT fields
    val defaultValue: String? = null,
    val validationRules: String? = null, // Stored as JSON
    val order: Int = 0
)

enum class FieldType {
    NUMBER,
    DURATION,
    TEXT
}
