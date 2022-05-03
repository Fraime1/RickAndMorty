package com.fraime.android.rm.domain.model

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val status: String,
    val image : String,
    val location : Location,
    val episode: ArrayList<String>
    )

