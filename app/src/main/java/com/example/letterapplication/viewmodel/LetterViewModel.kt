package com.example.letterapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.letterapplication.repository.LetterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LetterViewModel @Inject constructor(
  private val letterRepository: LetterRepository
): ViewModel() {
}