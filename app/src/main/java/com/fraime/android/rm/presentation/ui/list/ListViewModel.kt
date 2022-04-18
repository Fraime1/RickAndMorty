package com.fraime.android.rm.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fraime.android.rm.domain.model.Character
import com.fraime.android.rm.domain.usecase.GetCharactersPagingUseCase

class ListViewModel(
    getCharactersPagingUseCase: GetCharactersPagingUseCase
) : ViewModel() {

    val listCharacter: LiveData<PagingData<Character>> = getCharactersPagingUseCase.execute().cachedIn(viewModelScope)

}