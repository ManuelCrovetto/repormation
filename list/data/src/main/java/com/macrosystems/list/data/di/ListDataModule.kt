package com.macrosystems.list.data.di

import com.macrosystems.list.data.UserGithubRepositoryImpl
import com.macrosystems.list.domain.UserGithubRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val listDataModule = module {
    singleOf(::UserGithubRepositoryImpl).bind<UserGithubRepository>()
}