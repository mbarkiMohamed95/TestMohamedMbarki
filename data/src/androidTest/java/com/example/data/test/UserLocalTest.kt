package com.example.data.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.LargeTest
import com.example.data.KoinTestRule
import com.example.data.di.localTestModule
import com.example.data.local.LocalDataBaseUserTest
import com.example.data.local.entitys.LocalUserLocationModel
import com.example.data.local.entitys.LocalUserModel
import com.example.data.local.entitys.LocalUserPictureModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.inject

@LargeTest
class UserLocalTest : KoinTest {
    /**
     * Override default Koin configuration to use Room in-memory database
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = listOf(localTestModule)
    )

    private val userTestModel = LocalUserModel(
        "d08b3b28-4e28-41bf-8478-90abee67b075",
        "Mr",
        "Mohamed",
        "Mbarki",
        "moh@m.fr",
        "0648626802",
        "Homme",
        LocalUserLocationModel("24", "Paris", "ville d'avary", "france", "93410"),
        LocalUserPictureModel(
            "https://randomuser.me/api/portraits/thumb/men/72.jpg",
            "https://randomuser.me/api/portraits/thumb/men/72.jpg",
            "https://randomuser.me/api/portraits/thumb/men/72.jpg"
        )
    )

    private val localDataBaseUserTest: LocalDataBaseUserTest by inject(qualifier = named("localDataBaseUserTest"))


    @Test
    fun insertUserTest() {
        CoroutineScope(Dispatchers.IO).launch  {
            localDataBaseUserTest.insetUser(userTestModel)
            delay(100)
            Pager(PagingConfig(pageSize = 20)) { localDataBaseUserTest.getAllUsers() }.flow.collect {
                it.map { user ->
                    assertThat(user).isNotNull()
                }
            }
        }
    }

    @Test
    fun deleteUserTest() {
        CoroutineScope(Dispatchers.IO).launch {
            localDataBaseUserTest.insetUser(userTestModel)
            delay(100)
            localDataBaseUserTest.deleteAllUsers()
            Pager(PagingConfig(pageSize = 20)) { localDataBaseUserTest.getAllUsers() }.flow.collect {
                it.map { user ->
                    assertThat(user).isNull()
                }
            }

        }
    }

    @After
    fun tearDown() {
      stopKoin()
    }

}