package com.example.robottest.roboter

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule

class LoginScreenRoboter(private val composeContentTestRule: ComposeContentTestRule) {

    private companion object {
        const val emailContentDescription = "Login Email"
        const val passwordContentDescription = "Login Password"
        const val loginTitleContentDescription = "Login Title"
        const val loginErrorContentDescription = "Login failed"
        const val loginErrorButtonContentDescription = "Login Error Button"
    }

    fun waitToBeDisplayed() {
        composeContentTestRule.onNodeWithContentDescription(loginTitleContentDescription)
            .assertIsDisplayed()
    }

    fun enterEmail(email: String) {
        composeContentTestRule.onNodeWithContentDescription(emailContentDescription)
            .performTextClearance()
        composeContentTestRule.onNodeWithContentDescription(emailContentDescription)
            .performTextInput(email)
    }

    fun pressNext() {
        composeContentTestRule.onNodeWithContentDescription(emailContentDescription)
            .performImeAction()
    }

    fun enterPassword(password: String) {
        composeContentTestRule.onNodeWithContentDescription(passwordContentDescription)
            .performTextClearance()
        composeContentTestRule.onNodeWithContentDescription(passwordContentDescription)
            .performTextInput(password)
    }

    fun pressDone() {
        composeContentTestRule.onNodeWithContentDescription(passwordContentDescription)
            .performImeAction()
    }

    fun waitUntilErrorDisplayed() {
        composeContentTestRule.waitUntil(timeoutMillis = 10000) {
            composeContentTestRule.onAllNodesWithContentDescription(
                loginErrorContentDescription
            ).fetchSemanticsNodes().size == 1
        }
    }

    fun confirmError() {
        composeContentTestRule.onNodeWithContentDescription(loginErrorButtonContentDescription)
            .performClick()
    }
}