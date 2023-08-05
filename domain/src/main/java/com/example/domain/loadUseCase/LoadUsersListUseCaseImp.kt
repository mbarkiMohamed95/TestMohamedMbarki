package com.example.domain.loadUseCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.domain.loadUseCase.model.UserModel
import com.example.domain.repo.UserRepository
import com.example.domain.repo.model.RepoUserModel
import com.example.domain.tools.paging.PagerService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoadUsersListUseCaseImp(private val userRepository: UserRepository) : LoadUsersListUseCase {
    override fun invoke(isConnected: Boolean): Flow<PagingData<UserModel>> = if (isConnected) {
        userRepository.loadUsersAsFlow().map {
            it.map { item ->
                UserModel(
                    item.uuid,
                    item.name?.first ?: "",
                    item.name?.last ?: "",
                    item.picture?.thumbnail ?: "",
                    item.phone
                )
            }
        }
    } else userRepository.loadUsersFromLocal().map {
        it.map { item ->
            UserModel(
                item.uuid,
                item.name?.first ?: "",
                item.name?.last ?: "",
                item.picture?.thumbnail ?: "",
                item.phone
            )
        }
    }
}