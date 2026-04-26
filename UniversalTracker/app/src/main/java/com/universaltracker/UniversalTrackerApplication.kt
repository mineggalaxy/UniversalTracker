package com.universaltracker

import android.app.Application
import com.universaltracker.data.local.database.AppDatabase

class UniversalTrackerApplication : Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}
