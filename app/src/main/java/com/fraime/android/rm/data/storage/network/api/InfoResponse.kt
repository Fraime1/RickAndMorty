package com.fraime.android.rm.data.storage.network.api

import androidx.annotation.IntRange
import com.google.gson.annotations.SerializedName

class InfoResponse {
    @SerializedName("count")val count: Int? = null
    @SerializedName("pages")@IntRange(from = 1)val pages: Int? = null
    @SerializedName("next")val next: String? = null
    @SerializedName("prev")val prev: String? = null
}