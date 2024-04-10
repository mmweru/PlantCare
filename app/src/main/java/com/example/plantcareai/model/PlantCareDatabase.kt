package com.example.plantcareai.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plantcareai.model.ClassifiedImage

@Database(entities = [ClassifiedImage::class], version = 1, exportSchema = false)
abstract class PlantCareDatabase : RoomDatabase() {
    abstract fun classifiedImageDao(): ClassifiedImageDao

    companion object {
        @Volatile
        private var Instance: PlantCareDatabase? = null
        fun getDatabase(context: Context): PlantCareDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PlantCareDatabase::class.java, "ac_db")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}