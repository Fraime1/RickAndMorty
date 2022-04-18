package com.fraime.android.rm.presentation.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fraime.android.rm.domain.model.Character

class ListItemViewModel: ViewModel() {


    val id: MutableLiveData<Int?> = MutableLiveData()
    val name: MutableLiveData<String?> = MutableLiveData()
    val species: MutableLiveData<String?> = MutableLiveData()
    val gender: MutableLiveData<String?> = MutableLiveData()

    var character: Character? = null
        set(character) {
            field = character
            id.postValue(character?.id)
            name.postValue(character?.name)
            gender.postValue(character?.gender)
            species.postValue(character?.species)
        }
}