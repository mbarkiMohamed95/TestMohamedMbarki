package com.example.data.local


import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.KoinTestRule
import com.example.data.di.localTestModule
import com.example.data.local.entitys.LocalUserLocationModel
import com.example.data.local.entitys.LocalUserModel
import com.example.data.local.entitys.LocalUserPictureModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class UserLocalTest : KoinTest {


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

    private val localDataBaseUserTest: LocalDataBaseUserTest by inject()


    @Test
    fun insertUserTest() = runBlocking {
        localDataBaseUserTest.insetUser(userTestModel)
        delay(100)
        assertThat(localDataBaseUserTest.getAllUsers()).isNotEmpty()
    }

    @Test
    fun searchUserByNameWithThreeCharacterTest() = runBlocking {
        localDataBaseUserTest.insetUser(userTestModel)
        delay(100)
        assertThat(localDataBaseUserTest.searchUser("moh")).isNotNull()
        assertThat(
            localDataBaseUserTest.searchUser("moh").get(0).firstName
        ).isEqualTo("Mohamed")
    }

    @Test
    fun searchUserByNameWithThreeCharacterFaildTest() = runBlocking {
        localDataBaseUserTest.insetUser(userTestModel)
        delay(100)
        assertThat(
            localDataBaseUserTest.searchUser("moh").get(0).firstName
        ).isNotEqualTo("Alex")

    }

    @Test
    fun searchUserByLastNameWithThreeCharacterTest() = runBlocking {
        localDataBaseUserTest.insetUser(userTestModel)
        delay(100)
        var data = localDataBaseUserTest.searchUser("mba")
        assertThat(data).isNotEmpty()
        assertThat(data.get(0).firstName).isEqualTo("Mohamed")

    }


    @Test
    fun deleteUserTest() = runBlocking {
        localDataBaseUserTest.insetUser(userTestModel)
        delay(100)
        localDataBaseUserTest.deleteAllUsers()
        assertThat(localDataBaseUserTest.getAllUsers()).isEmpty()

    }
}


