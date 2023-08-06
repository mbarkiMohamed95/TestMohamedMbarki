package com.example.CoTest.di

import android.system.Os.bind
import com.example.domain.tools.paging.PagerService
import com.example.CoTest.tools.pagination.PagerServicesImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val  servicesModule = module {
    factoryOf(::PagerServicesImp) { bind<PagerService>() }

}