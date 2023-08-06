package com.example.data.repository.user

import android.location.Location
import com.example.data.local.entitys.LocalUserModel
import com.example.data.local.localManager.LocalUsersManager
import com.example.data.networking.userInfo.manager.UserNetworkManager
import com.example.data.repository.user.userDto.LocalToRepoUserMapper
import com.example.data.repository.user.userDto.RemoteToLocalUserMapper
import com.example.data.repository.user.userDto.UserDetailMapper
import com.example.data.tools.runCatchingAndMapToListDomain
import com.example.data.repository.location.LocationProvider
import com.example.domain.repo.user.UserRepository
import com.example.domain.repo.user.model.UserDetailDtoModel
import com.example.domain.repo.user.model.UserModelDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext


class UserRepositoryImp constructor(
    private val localUsersManager: LocalUsersManager,
    private val userNetworkManager: UserNetworkManager,
    private val remoteToLocalUserMapper: RemoteToLocalUserMapper,
    private val localToRepoUserMapper: LocalToRepoUserMapper,
    private val userDetailMapper: UserDetailMapper,
    private val locationProvider: LocationProvider,
    private val ioDispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun loadUsers(page: Int): Result<List<UserModelDto>> =
        Result.runCatchingAndMapToListDomain(localToRepoUserMapper) {
            withContext(ioDispatcher) {
                userNetworkManager.loadUsers(page).onSuccess { userRep ->
                    localUsersManager.deleteAllUsers()
                    remoteToLocalUserMapper.mapInputToOutput(userRep.results).also { localItem ->
                        localItem.map { it.pageNumber = userRep.info.page }
                        locationProvider.getCurrentLocation(this).first().let {
                            sortUserByLocations(localItem, it).let { users ->
                                localUsersManager.saveUserList(users)
                            }
                        }
                    }
                }
                localUsersManager.loadAllUsersAsResult()
            }
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

    private fun sortUserByLocations(
        locations: List<LocalUserModel>,
        myLocation: Location
    ): List<LocalUserModel> {
        var userLocation = Location("")
        return locations.sortedBy {
            userLocation.longitude = it.coordination?.longitude ?: 0.0
            userLocation.latitude = it.coordination?.latitude ?: 0.0
            myLocation.distanceTo(userLocation)
        }
    }
}