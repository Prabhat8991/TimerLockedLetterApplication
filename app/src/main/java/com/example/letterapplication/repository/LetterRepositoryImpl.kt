package com.example.letterapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.example.letterapplication.database.DatabaseLetterModel
import com.example.letterapplication.database.LetterDatabase
import com.example.letterapplication.database.asUiModel
import com.example.letterapplication.model.LetterUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LetterRepositoryImpl @Inject constructor(private val letterDatabase: LetterDatabase): LetterRepository {

    var letters: LiveData<List<LetterUIModel>> = Transformations.map(letterDatabase.LetterDao().getLetter()){
        it.asUiModel()
    }
    lateinit var intermediateLetters: LiveData<List<LetterUIModel>>

    var updatedLetters: MutableLiveData<List<LetterUIModel>> = MutableLiveData()

    var updatedLettersObserver =
        Observer<List<LetterUIModel>> { updatedValues ->
            updatedLetters.value = updatedValues
        }

    override suspend fun saveLetter(letterModel: DatabaseLetterModel): Long {
        return withContext(Dispatchers.IO) {
            letterDatabase.LetterDao().insertAll(letterModel)
        }
    }

    override suspend fun updateLetter(isLocked: Boolean, id: Int) {
        withContext(Dispatchers.IO) {
            launch {
                letterDatabase.LetterDao().update(false, id)
                intermediateLetters = Transformations.map(letterDatabase.LetterDao().getLetter()) {
                    it.asUiModel()
                }
            }
        }
        withContext(Dispatchers.Main) {
            intermediateLetters.observeForever(updatedLettersObserver)
        }
    }
}