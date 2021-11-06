package com.example.letterapplication.di

import android.content.Context
import androidx.room.Room
import com.example.letterapplication.database.LetterDatabase
import com.example.letterapplication.database.dao.LetterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseModule {
    @Provides
    fun provideLetterDao(letterDatabase: LetterDatabase): LetterDao {
        return letterDatabase.LetterDao()
    }

    @Provides
    fun provideLetterDatabase(@ApplicationContext appContext: Context): LetterDatabase {
        return Room.databaseBuilder(
            appContext,
            LetterDatabase::class.java,
            "letter_database"
        ).build()
    }
}