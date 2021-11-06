package com.example.letterapplication.viewmodel

import com.example.letterapplication.repository.LetterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LetterViewModel @Inject constructor(
  private val letterRepository: LetterRepository
) {
}