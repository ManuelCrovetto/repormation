package com.macrosystems.detail.data.di

import com.macrosystems.detail.data.EventsRepositoryImpl
import com.macrosystems.detail.domain.EventsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val detailDataModule = module {
    singleOf(::EventsRepositoryImpl).bind<EventsRepository>()
}