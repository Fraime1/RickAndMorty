package com.fraime.android.rm.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.fraime.android.rm.domain.model.Character

interface CharactersRepository {

    fun getCharacters() : LiveData<List<Character>>

    fun getCharacter(id: String) : MutableLiveData<Character>

    fun getCharactersPaging() : LiveData<PagingData<Character>>
}