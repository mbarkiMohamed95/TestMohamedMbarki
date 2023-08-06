package com.example.data.local.localManager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.local.dao.userDao.UserDao
import com.example.data.local.entitys.LocalUserModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single


class LocalUsersManagerImp constructor(private val userDao: UserDao, private val context: Context) :
    LocalUsersManager {
    private val loadedPage = intPreferencesKey("LOADED_PAGE")

    override suspend fun saveUser(user: LocalUserModel) {
        userDao.insert(user)
    }

    override suspend fun saveUserList(users: List<LocalUserModel>) {
        userDao.insertList(users)
    }

    override suspend fun getLoadedPage(): Int = context.dataStore.data.map { preferences ->
        preferences[loadedPage] ?: 0
    }.single()

    override suspend fun saveLoadedPage(page: Int) {
        context.dataStore.edit { settings ->
            settings[loadedPage] = page
        }
    }

    override suspend fun deleteAllUsers() = userDao.deleteAll()

    override suspend fun loadAllUsers(): List<LocalUserModel> = userDao.loadUsers()
    override suspend fun loadAllUsersAsResult(): Result<List<LocalUserModel>> {
        return if (userDao.loadUsers().isNotEmpty()
        ) Result.success(userDao.loadUsers()) else Result.failure(Exception("empty data"))
    }

    override suspend fun loadUserById(id: String): LocalUserModel = userDao.loadUserById(id)
    override suspend fun searchUser(
        searchKey: String
    ): List<LocalUserModel> = userDao.searchUser("%${searchKey}%")
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
