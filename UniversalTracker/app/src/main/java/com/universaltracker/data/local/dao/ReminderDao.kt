package com.universaltracker.data.local.dao

import androidx.room.*
import com.universaltracker.data.local.model.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminders WHERE trackerId = :trackerId ORDER BY timeHour, timeMinute")
    fun getRemindersByTrackerId(trackerId: String): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun getReminderById(id: String): ReminderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderEntity)

    @Update
    suspend fun updateReminder(reminder: ReminderEntity)

    @Delete
    suspend fun deleteReminder(reminder: ReminderEntity)

    @Query("DELETE FROM reminders WHERE trackerId = :trackerId")
    suspend fun deleteAllRemindersForTracker(trackerId: String)

    @Query("SELECT * FROM reminders WHERE isEnabled = 1")
    fun getAllEnabledReminders(): Flow<List<ReminderEntity>>
}
