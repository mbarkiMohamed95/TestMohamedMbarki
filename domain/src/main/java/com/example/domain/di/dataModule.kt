package com.example.domain.di

import org.koin.dsl.module

val dataLayerModule = module {
    includes(dtoModule, networkingModule, repositoryModule, dataModule)
}