package com.macrosystems.list.data.di

import com.macrosystems.list.data.UserReposRepositoryImpl
import com.macrosystems.list.domain.UserReposRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val listDataModule = module {
    singleOf(::UserReposRepositoryImpl).bind<UserReposRepository>()
}