package com.example.plantcareai.firebaseauth

import android.content.Context
import com.example.plantcareai.model.ClassifiedImageDao
import com.example.plantcareai.model.ClassifiedImageRepository
import com.example.plantcareai.model.ClassifiedImageRepositoryImpl
import com.example.plantcareai.model.PlantCareDatabase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository{
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesClassifiedImageDao(plantCareDatabase: PlantCareDatabase): ClassifiedImageDao {
        return plantCareDatabase.classifiedImageDao()
    }

    @Provides
    @Singleton
    fun providesAppleCareDatabase(@ApplicationContext context: Context): PlantCareDatabase {
        return PlantCareDatabase.getDatabase(context)
    }

    @Provides
    fun providesClassifiedImageRepository(classifiedImageDao: ClassifiedImageDao): ClassifiedImageRepository {
        return ClassifiedImageRepositoryImpl(classifiedImageDao)
    }
}