package com.example.base.di

import com.example.data.di.dataModule
import com.example.data.di.dtoModule
import com.example.data.di.localModule
import com.example.data.di.networkingModule
import com.example.data.di.repositoryModule
import com.example.domain.di.domaineDi
import com.example.location.di.locationModule
import org.koin.dsl.module

val dataLayerModule = module {
    includes(dtoModule, networkingModule, repositoryModule, dataModule,localModule)
}

val domainLayerModules = module{
    includes(domaineDi)
}
val locationProviderModules = module{
    includes(locationModule)
}