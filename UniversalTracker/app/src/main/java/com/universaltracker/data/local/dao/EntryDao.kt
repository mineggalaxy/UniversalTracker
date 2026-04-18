package com.universaltracker.data.local.dao

import androidx.room.*
import com.universaltracker.data.local.model.EntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Query("SELECT * FROM entries WHERE trackerId = :trackerId ORDER BY recordedAt DESC")
    fun getEntriesByTrackerId(trackerId: String): Flow<List<EntryEntity>>

    @Query("SELECT * FROM entries WHERE trackerId = :trackerId AND recordedAt BETWEEN :startDate AND :endDate ORDER BY recordedAt ASC")
    fun getEntriesByTrackerIdAndDateRange(trackerId: String, startDate: Long, endDate: Long): Flow<List<EntryEntity>>

    @Query("SELECT * FROM entries WHERE id = :id")
    suspend fun getEntryById(id: String): EntryEntity?

    @Query("SELECT * FROM entries WHERE id = :id")
    fun getEntryByIdFlow(id: String): Flow<EntryEntity?>

    @Query("SELECT * FROM entries WHERE trackerId = :trackerId ORDER BY recordedAt DESC LIMIT 1")
    suspend fun getLatestEntryByTrackerId(trackerId: String): EntryEntity?

    @Query("SELECT * FROM entries WHERE notes LIKE :query OR imageUris LIKE :query")
    fun searchEntries(query: String): Flow<List<EntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: EntryEntity)

    @Update
    suspend fun updateEntry(entry: EntryEntity)

    @Delete
    suspend fun deleteEntry(entry: EntryEntity)

    @Query("DELETE FROM entries WHERE trackerId = :trackerId")
    suspend fun deleteAllEntriesForTracker(trackerId: String)
}
