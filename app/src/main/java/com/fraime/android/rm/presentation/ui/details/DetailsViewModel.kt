package com.fraime.android.rm.presentation.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fraime.android.rm.domain.model.Character
import com.fraime.android.rm.domain.usecase.GetCharacterUseCase

const val TAG = "DetailsViewModel"

class DetailsViewModel(private val getCharacterUseCase: GetCharacterUseCase) : ViewModel() {

    private val id: MutableLiveData<Int> = MutableLiveData()
    val character: LiveData<Character>

    init {
        character = Transformations.switchMap(id) {
            getCharacterUseCase.execute(id.value.toString())
        }
    }

    var name: MutableLiveData<String> = MutableLiveData()
    var species: MutableLiveData<String> = MutableLiveData()
    var gender: MutableLiveData<String> = MutableLiveData()
    var status: MutableLiveData<String> = MutableLiveData()
    var location: MutableLiveData<String> = MutableLiveData()
    var episodes: MutableLiveData<String> = MutableLiveData()


    fun loadId(id: Int) {
        this.id.value = id
    }

}