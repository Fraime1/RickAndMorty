package com.fraime.android.rm.presentation.di

import com.fraime.android.rm.domain.usecase.GetCharacterUseCase
import com.fraime.android.rm.domain.usecase.GetCharactersPagingUseCase
import org.koin.dsl.module


val domainModule = module {
    factory {
        GetCharacterUseCase(charactersRepository = get())
    }

    factory {
        GetCharactersPagingUseCase(charactersRepository = get())
    }
}