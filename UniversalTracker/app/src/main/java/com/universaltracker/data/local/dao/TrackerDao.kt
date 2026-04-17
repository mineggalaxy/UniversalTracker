package com.universaltracker.data.local.dao

import androidx.room.*
import com.universaltracker.data.local.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {
    @Query("SELECT * FROM trackers WHERE archived = :archived ORDER BY isPinned DESC, pinOrder ASC, createdAt DESC")
    fun getAllTrackers(archived: Boolean = false): Flow<List<TrackerEntity>>

    @Query("SELECT * FROM trackers WHERE id = :id")
    suspend fun getTrackerById(id: String): TrackerEntity?

    @Query("SELECT * FROM trackers WHERE id = :id")
    fun getTrackerByIdFlow(id: String): Flow<TrackerEntity?>

    @Query("SELECT * FROM trackers WHERE isPinned = 1 ORDER BY pinOrder ASC")
    fun getPinnedTrackers(): Flow<List<TrackerEntity>>

    @Query("SELECT * FROM trackers WHERE name LIKE :query OR tags LIKE :query")
    fun searchTrackers(query: String): Flow<List<TrackerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracker(tracker: TrackerEntity)

    @Update
    suspend fun updateTracker(tracker: TrackerEntity)

    @Delete
    suspend fun deleteTracker(tracker: TrackerEntity)

    @Query("UPDATE trackers SET isPinned = :isPinned, pinOrder = :pinOrder, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updatePinStatus(id: String, isPinned: Boolean, pinOrder: Int, updatedAt: Long)

    @Query("SELECT MAX(pinOrder) FROM trackers WHERE isPinned = 1")
    suspend fun getMaxPinOrder(): Int?
}
