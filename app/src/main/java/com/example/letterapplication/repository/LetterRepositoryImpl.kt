package com.example.letterapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.letterapplication.database.DatabaseLetterModel
import com.example.letterapplication.database.LetterDatabase
import com.example.letterapplication.database.asUiModel
import com.example.letterapplication.model.LetterUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LetterRepositoryImpl @Inject constructor(private val letterDatabase: LetterDatabase): LetterRepository {

    val letters: LiveData<List<LetterUIModel>> = Transformations.map(letterDatabase.LetterDao().getLetter()){
        it.asUiModel()
    }

    override suspend fun saveLetter(letterModel: DatabaseLetterModel) {
     withContext(Dispatchers.IO) {
         letterDatabase.LetterDao().insertAll(letterModel)
     }
    }
}