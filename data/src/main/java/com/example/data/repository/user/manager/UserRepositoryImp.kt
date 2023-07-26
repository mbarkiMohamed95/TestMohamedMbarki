package com.example.data.repository.user.manager

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.local.localManager.LocalUsersManager
import com.example.data.networking.userInfo.manager.UserNetworkManager
import com.example.data.repository.user.model.RepoUserModel
import com.example.data.repository.userDto.LocalToRepoUserMapper
import com.example.data.repository.userDto.RemoteToLocalUserMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImp constructor(
    private val localUsersManager: LocalUsersManager,
    private val userNetworkManager: UserNetworkManager,
    private val remoteToLocalUserMapper: RemoteToLocalUserMapper,
    private val localToRepoUserMapper: LocalToRepoUserMapper
) : UserRepository {
    override fun loadUsersAsFlow(): Flow<PagingData<RepoUserModel>> =
        Pager(
            pagingSourceFactory = { userNetworkManager },
            config = PagingConfig(pageSize = 20)
        ).flow.map {
            it.map { item ->
                remoteToLocalUserMapper.mapInputToOutput(item).also { localItem ->
                    localUsersManager.saveUser(localItem)
                }
            }
        }.map {
            it.map { itemRepo ->
                localToRepoUserMapper.mapInputToOutput(itemRepo)
            }
        }

    override fun loadUsersFromLocal(): Flow<PagingData<RepoUserModel>> =
        Pager(PagingConfig(pageSize = 20)) { localUsersManager.loadAllUsers() }.flow.map {
            it.map { itemRepo ->
                localToRepoUserMapper.mapInputToOutput(itemRepo)
            }
        }


}