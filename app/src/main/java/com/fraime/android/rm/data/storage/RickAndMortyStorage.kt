package com.fraime.android.rm.data.storage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.fraime.android.rm.data.storage.network.api.RickAndMortyApi
import com.fraime.android.rm.domain.model.Character


interface RickAndMortyStorage {

    fun get() : LiveData<List<Character>>

    fun getOne(id: String) : MutableLiveData<Character>

    fun getPaging() : RickAndMortyApi
}