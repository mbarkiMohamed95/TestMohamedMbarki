package com.example.CoTest.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.CoTest.presentation.mainScreen.MainScreen
import com.example.CoTest.routing.Navigation
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {
            navController =
                TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            MainScreen()
        }
    }

    @Test
    fun testSearch() {
        composeTestRule
            .onNodeWithTag("searchAction")
            .performClick()
        composeTestRule
            .onNodeWithTag("searchtext")
            .assertIsDisplayed()
    }


}