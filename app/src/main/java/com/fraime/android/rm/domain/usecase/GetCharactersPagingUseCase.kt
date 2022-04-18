package com.fraime.android.rm.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.fraime.android.rm.domain.model.Character
import com.fraime.android.rm.domain.repository.CharactersRepository

class GetCharactersPagingUseCase(private val charactersRepository: CharactersRepository) {
    fun execute() : LiveData<PagingData<Character>> {
       return  charactersRepository.getCharactersPaging()
    }
}