package com.macrosystems.presentation.di

import com.macrosystems.presentation.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val detailViewModelModule = module {
    viewModelOf(::DetailViewModel)
}