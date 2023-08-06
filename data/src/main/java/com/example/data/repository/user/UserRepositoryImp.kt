package com.example.data.repository.user

import com.example.data.local.localManager.LocalUsersManager
import com.example.data.networking.userInfo.manager.UserNetworkManager
import com.example.data.repository.user.userDto.LocalToRepoUserMapper
import com.example.data.repository.user.userDto.RemoteToLocalUserMapper
import com.example.data.repository.user.userDto.UserDetailMapper
import com.example.data.tools.runCatchingAndMapToListDomain
import com.example.domain.repo.user.UserRepository
import com.example.domain.repo.user.model.UserDetailDtoModel
import com.example.domain.repo.user.model.UserModelDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImp constructor(
    private val localUsersManager: LocalUsersManager,
    private val userNetworkManager: UserNetworkManager,
    private val remoteToLocalUserMapper: RemoteToLocalUserMapper,
    private val localToRepoUserMapper: LocalToRepoUserMapper,
    private val userDetailMapper: UserDetailMapper
) : UserRepository {

    override suspend fun loadUsers(page: Int): Result<List<UserModelDto>> =
        Result.runCatchingAndMapToListDomain(localToRepoUserMapper) {
            userNetworkManager.loadUsers(page).onSuccess {userRep->
                remoteToLocalUserMapper.mapInputToOutput(userRep.results).also { localItem ->
                    localItem.map { it.pageNumber = userRep.info.page }
                    localUsersManager.saveUserList(localItem)
                }
            }
            localUsersManager.loadAllUsersAsResult()
        }


    override suspend fun loadUserById(id: String): UserDetailDtoModel =
        userDetailMapper.mapInputToOutput(localUsersManager.loadUserById(id))

    override suspend fun deleteAllUsers() = localUsersManager.deleteAllUsers()

    override suspend fun searchUser(
        searchKey: String
    ): List<UserModelDto> = localToRepoUserMapper.mapInputToOutput(
        localUsersManager.searchUser(
            searchKey
        )
    )

}