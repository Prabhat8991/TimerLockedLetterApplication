package com.example.letterapplication.di

import com.example.letterapplication.repository.LetterRepository
import com.example.letterapplication.repository.LetterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class LetterModule {
    @Binds
    abstract fun provideLetterRepository(letterRepositoryImpl: LetterRepositoryImpl): LetterRepository
}