package com.example.repositorieschallenge

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.repositorieschallenge.presentation.RepositoriesActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(RepositoriesActivity::class.java)

    @Test
    fun checkScreenLoaded() {
        onView(withId(R.id.search_layout))
            .check(matches(isDisplayed()))

        onView(withId(R.id.label_name)).perform(typeText("Repo Name:"))
    }
}