package com.example.letterapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letterapplication.database.DatabaseLetterModel
import com.example.letterapplication.model.LetterUIModel
import com.example.letterapplication.repository.LetterRepository
import com.example.letterapplication.repository.LetterRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LetterViewModel @Inject constructor(
  private val letterRepository: LetterRepository
): ViewModel() {

  private val _nextId: MutableLiveData<Long> = MutableLiveData()

  val nextId: LiveData<Long>
  get() = _nextId

  val letters = (letterRepository as LetterRepositoryImpl).letters

  val updatedLetters: MutableLiveData<List<LetterUIModel>> =  (letterRepository as LetterRepositoryImpl).updatedLetters

  fun saveLetters(letterModel: DatabaseLetterModel) {
    var nextId: Long = 0
     viewModelScope.launch {
       nextId = letterRepository.saveLetter(letterModel)
       _nextId.postValue(nextId)
    }
  }
}