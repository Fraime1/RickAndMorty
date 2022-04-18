package com.fraime.android.rm.data.storage.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fraime.android.rm.data.storage.RickAndMortyStorage
import com.fraime.android.rm.data.storage.network.api.RickAndMortyApi
import com.fraime.android.rm.data.storage.network.retrofit.RetrofitRickAndMortyStorage
import com.fraime.android.rm.domain.model.Character
import retrofit2.HttpException

const val TAG = "RandMPageSource"

class RandMPageSource(
    private val rickAndMortyStorage: RickAndMortyStorage,
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page: Int = params.key ?: 1
        val response = rickAndMortyStorage.getPaging().getPagingCharacters(page)
        if (response.isSuccessful) {
            Log.d(TAG, "response received")
            val charactersList = response.body()?.characterList ?: mutableListOf()

            return LoadResult.Page(data = charactersList,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if(charactersList.isEmpty()) null else page + 1)
        } else {
            return LoadResult.Error(HttpException(response))
        }
    }


}