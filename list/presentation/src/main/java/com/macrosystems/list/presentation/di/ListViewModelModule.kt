package com.macrosystems.list.presentation.di

import com.macrosystems.list.presentation.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val listViewModelModule = module {
    viewModelOf(::ListViewModel)
}