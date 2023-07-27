package com.example.data.local.dao.userDao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.data.local.dao.baseDAO.BaseDao
import com.example.data.local.entitys.LocalUserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao: BaseDao<LocalUserModel> {

    @Query("SELECT * FROM LocalUserModel")
     fun loadAllUsers(): PagingSource<Int, LocalUserModel>
    @Query("SELECT * FROM LocalUserModel WHERE :id LIKE uuid")
     suspend fun loadUserById(id:String): LocalUserModel

}