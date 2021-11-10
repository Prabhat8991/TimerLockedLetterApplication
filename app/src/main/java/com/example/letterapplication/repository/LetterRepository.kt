package com.example.letterapplication.repository

import com.example.letterapplication.database.DatabaseLetterModel

interface LetterRepository {
    suspend fun saveLetter(letterModel: DatabaseLetterModel): Long
    suspend fun updateLetter(isLocked:Boolean = false, id: Int)
}