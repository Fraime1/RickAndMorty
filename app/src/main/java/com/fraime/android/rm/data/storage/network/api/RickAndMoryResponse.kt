package com.fraime.android.rm.data.storage.network.api

import com.fraime.android.rm.domain.model.Character
import com.google.gson.annotations.SerializedName

class RickAndMoryResponse {
    @SerializedName("results")
    lateinit var characterList: List<Character>

    lateinit var info : InfoResponse
}