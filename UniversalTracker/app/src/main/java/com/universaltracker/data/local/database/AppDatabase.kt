package com.universaltracker.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.universaltracker.data.local.dao.*
import com.universaltracker.data.local.model.*

@Database(
    entities = [
        TrackerEntity::class,
        TrackerFieldEntity::class,
        EntryEntity::class,
        EntryFieldValueEntity::class,
        ReminderEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackerDao(): TrackerDao
    abstract fun trackerFieldDao(): TrackerFieldDao
    abstract fun entryDao(): EntryDao
    abstract fun entryFieldValueDao(): EntryFieldValueDao
    abstract fun reminderDao(): ReminderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "universal_tracker_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
