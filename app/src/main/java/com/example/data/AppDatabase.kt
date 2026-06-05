package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Aircraft::class,
        ConciergeRequest::class,
        SecurityAssessment::class,
        CrewCandidate::class,
        MaintenanceLog::class,
        FuelQuote::class,
        CharterListing::class,
        ComplianceCheck::class,
        HangarDevelopment::class,
        CarbonOffsetLog::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun prestigeJetDao(): PrestigeJetDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "prestige_jet_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
