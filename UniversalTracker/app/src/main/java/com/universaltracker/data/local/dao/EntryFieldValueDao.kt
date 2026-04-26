package com.universaltracker.data.local.dao

import androidx.room.*
import com.universaltracker.data.local.model.EntryFieldValueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryFieldValueDao {
    @Query("SELECT * FROM entry_field_values WHERE entryId = :entryId")
    fun getValuesByEntryId(entryId: String): Flow<List<EntryFieldValueEntity>>

    @Query("SELECT * FROM entry_field_values WHERE entryId = :entryId AND fieldId = :fieldId")
    suspend fun getValueByEntryIdAndFieldId(entryId: String, fieldId: String): EntryFieldValueEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertValue(value: EntryFieldValueEntity)

    @Update
    suspend fun updateValue(value: EntryFieldValueEntity)

    @Delete
    suspend fun deleteValue(value: EntryFieldValueEntity)

    @Query("DELETE FROM entry_field_values WHERE entryId = :entryId")
    suspend fun deleteAllValuesForEntry(entryId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllValues(values: List<EntryFieldValueEntity>)
}
