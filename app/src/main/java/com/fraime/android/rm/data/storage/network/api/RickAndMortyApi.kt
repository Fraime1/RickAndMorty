package com.fraime.android.rm.data.storage.network.api

import androidx.annotation.IntRange
import com.fraime.android.rm.domain.model.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    fun getCharacters() : Call<RickAndMoryResponse>

    @GET("character/")
    suspend fun getPagingCharacters(@Query("page") @IntRange(from = 1) page: Int = 1) : Response<RickAndMoryResponse>

    @GET("character/{id}")
    fun getCharacter(@Path("id")id: String) : Call<Character>
}