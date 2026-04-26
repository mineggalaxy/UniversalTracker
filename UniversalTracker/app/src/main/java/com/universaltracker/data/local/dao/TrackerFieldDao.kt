package com.universaltracker.data.local.dao

import androidx.room.*
import com.universaltracker.data.local.model.TrackerFieldEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerFieldDao {
    @Query("SELECT * FROM tracker_fields WHERE trackerId = :trackerId ORDER BY `order` ASC")
    fun getFieldsByTrackerId(trackerId: String): Flow<List<TrackerFieldEntity>>

    @Query("SELECT * FROM tracker_fields WHERE id = :id")
    suspend fun getFieldById(id: String): TrackerFieldEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertField(field: TrackerFieldEntity)

    @Update
    suspend fun updateField(field: TrackerFieldEntity)

    @Delete
    suspend fun deleteField(field: TrackerFieldEntity)

    @Query("DELETE FROM tracker_fields WHERE trackerId = :trackerId")
    suspend fun deleteAllFieldsForTracker(trackerId: String)

    @Query("SELECT MAX(`order`) FROM tracker_fields WHERE trackerId = :trackerId")
    suspend fun getMaxOrder(trackerId: String): Int?
}
