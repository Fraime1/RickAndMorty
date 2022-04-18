package com.fraime.android.rm.domain.model

data class Character(
    val id: Int,
    var name: String,
    var species: String,
    var gender: String,
    var status: String,
    var image : String,
    var location : Location,
    var episode: ArrayList<String>
    )

