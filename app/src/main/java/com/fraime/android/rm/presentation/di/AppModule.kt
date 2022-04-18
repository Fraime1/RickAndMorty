package com.fraime.android.rm.presentation.di

import com.fraime.android.rm.presentation.ui.details.DetailsViewModel
import com.fraime.android.rm.presentation.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel<ListViewModel>{
        ListViewModel(
            getCharactersPagingUseCase = get()
        )
    }

    viewModel<DetailsViewModel> {
        DetailsViewModel(getCharacterUseCase = get())
    }
}