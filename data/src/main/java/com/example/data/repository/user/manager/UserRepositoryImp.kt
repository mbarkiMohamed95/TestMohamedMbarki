package com.example.data.repository.user.manager

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.local.localManager.LocalUsersManager
import com.example.data.networking.userInfo.manager.UserNetworkManager
import com.example.domain.repo.model.RepoUserModel
import com.example.domain.repo.model.UserDetailRepoModel
import com.example.data.repository.userDto.LocalToRepoUserMapper
import com.example.data.repository.userDto.RemoteToLocalUserMapper
import com.example.data.repository.userDto.UserDetailMapper
import com.example.data.tools.runCatchingAndMapToListDomain
import com.example.domain.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UserRepositoryImp constructor(
    private val localUsersManager: LocalUsersManager,
    private val userNetworkManager: UserNetworkManager,
    private val remoteToLocalUserMapper: RemoteToLocalUserMapper,
    private val localToRepoUserMapper: LocalToRepoUserMapper,
    private val userDetailMapper: UserDetailMapper
) : UserRepository {
    override fun loadUsersAsFlow(page: Int): Flow<Result<List<RepoUserModel>>> = flow {
        userNetworkManager.loadUsers(page).onSuccess {
            remoteToLocalUserMapper.mapInputToOutput(it.results).also { localItem ->
                localUsersManager.saveUserList(localItem)
            }
            emit(Result.success(localToRepoUserMapper.mapInputToOutput(localUsersManager.loadAllUsers())))
        }.onFailure {
            if (localUsersManager.loadAllUsers().isEmpty()) {
                emit(Result.success(localToRepoUserMapper.mapInputToOutput(localUsersManager.loadAllUsers())))
            } else {
                emit(Result.failure(Exception("no data found")))
            }
        }
    }

    override suspend fun loadUsers(page: Int): Result<List<RepoUserModel>> =
        Result.runCatchingAndMapToListDomain(localToRepoUserMapper) {
            userNetworkManager.loadUsers(page).onSuccess {
                remoteToLocalUserMapper.mapInputToOutput(it.results).also { localItem ->
                    localUsersManager.saveUserList(localItem)
                }
            }

            localUsersManager.loadAllUsersAsResult()
        }


    override suspend fun loadUserById(id: String): UserDetailRepoModel =
        userDetailMapper.mapInputToOutput(localUsersManager.loadUserById(id))

    override suspend fun deleteAllUsers() = localUsersManager.deleteAllUsers()

    override suspend fun searchUser(
        searchKey: String
    ): List<RepoUserModel> = localToRepoUserMapper.mapInputToOutput(
        localUsersManager.searchUser(
            searchKey
        )
    )


}