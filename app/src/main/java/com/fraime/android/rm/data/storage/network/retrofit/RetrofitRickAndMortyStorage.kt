package com.fraime.android.rm.data.storage.network.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fraime.android.rm.data.storage.RickAndMortyStorage
import com.fraime.android.rm.data.storage.network.api.RickAndMortyApi
import com.fraime.android.rm.data.storage.network.api.RickAndMoryResponse
import com.fraime.android.rm.domain.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "RetrofitRickAndMortyStorage"

class RetrofitRickAndMortyStorage : RickAndMortyStorage {

    private val rickAndMortyApi: RickAndMortyApi

    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        rickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)
    }

    override fun get(): LiveData<List<Character>> {
        val responseLiveData : MutableLiveData<List<Character>> = MutableLiveData()

        val request : Call<RickAndMoryResponse> = rickAndMortyApi.getCharacters()

        request.enqueue(object : Callback<RickAndMoryResponse> {
            override fun onResponse(
                call: Call<RickAndMoryResponse>,
                response: Response<RickAndMoryResponse>,
            ) {
                Log.d(TAG,"Response received")
                val rickAndMoryResponse: RickAndMoryResponse? = response.body()
                val characterList: List<Character> = rickAndMoryResponse?.characterList ?: mutableListOf()
                Log.d(TAG, "${characterList.size}")

                responseLiveData.value = characterList

            }

            override fun onFailure(call: Call<RickAndMoryResponse>, t: Throwable) {
                Log.d(TAG, "Response failed")
            }

        })

        return responseLiveData
    }

    override fun getOne(id: String): MutableLiveData<Character> {
        val responseLiveData: MutableLiveData<Character> = MutableLiveData()

        val request : Call<Character> = rickAndMortyApi.getCharacter(id)

        request.enqueue(object : Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: Response<Character>,
            ) {
                Log.d(TAG,"Response oneCharacter received - id:$id")
                val character = response.body()

                responseLiveData.value = character
                Log.d(TAG, "response name = ${responseLiveData.value?.name}")
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d(TAG, "Response oneCharacter failed")
            }

        })

        return responseLiveData

    }

    override fun getPaging(): RickAndMortyApi{
        return rickAndMortyApi
    }
}