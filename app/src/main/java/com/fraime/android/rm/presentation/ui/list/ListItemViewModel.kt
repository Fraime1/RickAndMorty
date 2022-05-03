package com.fraime.android.rm.presentation.ui.list

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.fraime.android.rm.R
import com.fraime.android.rm.domain.model.Character
import com.fraime.android.rm.presentation.ui.list.adapters.RcAdapter

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

    fun toDetails(navController: NavController, id: Int?) {
        navController.navigate(
            R.id.action_listFragment_to_detailsFragment,
            bundleOf(RcAdapter.ID_KEY to id)
        )

    }
}