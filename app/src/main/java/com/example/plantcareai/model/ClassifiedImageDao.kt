package com.example.plantcareai.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassifiedImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(classifiedImage: ClassifiedImage)

    @Query("SELECT * FROM classified_images ORDER BY id ASC")
    fun getAllClassifiedImages(): Flow<List<ClassifiedImage>>

    @Delete
    suspend fun delete(classifiedImage: ClassifiedImage)
}