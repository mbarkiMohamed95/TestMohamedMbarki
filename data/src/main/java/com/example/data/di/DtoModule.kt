package com.example.data.di

import com.example.data.repository.userDto.RemoteToLocalUserMapper
import com.example.data.repository.userDto.LocalToRepoUserMapper
import com.example.data.base.MappingService
import com.example.data.local.entitys.LocalUserModel
import com.example.data.networking.userInfo.model.Results
import com.example.data.repository.user.model.RepoUserModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val dtoModule = module {
    singleOf(::RemoteToLocalUserMapper) { bind<MappingService<Results, LocalUserModel>>() }
    singleOf(::LocalToRepoUserMapper) { bind<MappingService<LocalUserModel, RepoUserModel>>() }
}
