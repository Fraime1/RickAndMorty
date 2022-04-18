package com.fraime.android.rm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.fraime.android.rm.data.storage.RickAndMortyStorage
import com.fraime.android.rm.data.storage.network.RandMPageSource
import com.fraime.android.rm.domain.model.Character
import com.fraime.android.rm.domain.repository.CharactersRepository

class CharactersRepositoryImpl(
    private val rickAndMortyStorage: RickAndMortyStorage) :
    CharactersRepository {
    override fun getCharacters(): LiveData<List<Character>> {
        return rickAndMortyStorage.get()
    }

    override fun getCharacter(id: String): MutableLiveData<Character> {
        return rickAndMortyStorage.getOne(id)
    }

    override fun getCharactersPaging(): LiveData<PagingData<Character>> {
        return Pager(PagingConfig(pageSize = 20)) {
            RandMPageSource(rickAndMortyStorage)
        }.liveData
    }
}