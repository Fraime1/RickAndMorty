package com.fraime.android.rm.presentation.di

import com.fraime.android.rm.data.repository.CharactersRepositoryImpl
import com.fraime.android.rm.data.storage.RickAndMortyStorage
import com.fraime.android.rm.data.storage.network.RandMPageSource
import com.fraime.android.rm.data.storage.network.retrofit.RetrofitRickAndMortyStorage
import com.fraime.android.rm.domain.repository.CharactersRepository
import org.koin.dsl.module

val dataModule = module {
    single<CharactersRepository> {
        CharactersRepositoryImpl(
            rickAndMortyStorage = get()
        )
    }

    single<RickAndMortyStorage> {
        RetrofitRickAndMortyStorage()
    }
}