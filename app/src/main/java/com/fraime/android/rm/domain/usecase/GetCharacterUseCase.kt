package com.fraime.android.rm.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fraime.android.rm.domain.model.Character
import com.fraime.android.rm.domain.repository.CharactersRepository

class GetCharacterUseCase(private val charactersRepository: CharactersRepository) {

    fun execute(id: String) : MutableLiveData<Character> {
        return charactersRepository.getCharacter(id)
    }
}