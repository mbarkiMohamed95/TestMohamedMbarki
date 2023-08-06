package com.example.CoTest.di

import com.example.CoTest.tools.pagination.PagerServicesImp
import com.example.CoTest.tools.pagination.PagerService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val  servicesModule = module {
    factoryOf(::PagerServicesImp) { bind<PagerService>() }

}