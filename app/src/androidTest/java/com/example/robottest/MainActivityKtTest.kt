package com.example.robottest

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.robottest.roboter.HomeScreenRoboter
import com.example.robottest.roboter.LoginScreenRoboter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class MainActivityKtTest {

    @get:Rule
    val composeActivityTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun verifyThatAfterEnteringWrongCredentialsAnErrorWillBeDisplayed() {
        with(LoginScreenRoboter(composeActivityTestRule)) {
            waitToBeDisplayed()
            enterEmail("myEmail@abc.com")
            pressNext()
            enterPassword("d23d2d")
            pressDone()
            waitUntilErrorDisplayed()
        }
    }

    @Test
    fun verifyThatEnteringCorrectCredentialsAfterErrorStillWorks() {
        with(LoginScreenRoboter(composeActivityTestRule)) {
            waitToBeDisplayed()
            enterEmail("myEmail@abc.com")
            pressNext()
            enterPassword("d23d2d")
            pressDone()
            waitUntilErrorDisplayed()
            confirmError()
            enterEmail("abc@mail.de")
            pressNext()
            enterPassword("123456")
            pressDone()
        }

        with(HomeScreenRoboter(composeActivityTestRule)) {
            waitToBeDisplayed()
        }
    }
}