package com.example.robottest.roboter

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule

class HomeScreenRoboter(private val composeContentTestRule: ComposeContentTestRule) {

    private companion object {
        const val homeContentDescription = "Home"
    }

    fun waitToBeDisplayed() {
        composeContentTestRule.waitUntil(timeoutMillis = 10000) {
            composeContentTestRule.onAllNodesWithContentDescription(
                homeContentDescription
            ).fetchSemanticsNodes().size == 1
        }
        composeContentTestRule.onNodeWithContentDescription(homeContentDescription).assertIsDisplayed()
    }
}