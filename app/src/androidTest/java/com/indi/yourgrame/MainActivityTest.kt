package com.indi.yourgrame

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.indi.yourgrame.ui.list.view.SearchListActivity
import com.indi.yourgrame.ui.main.view.MainActivity
import com.indi.yourgrame.ui.vm.YourGrameViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testActivityCreated() {
        val scenario = launchActivity<MainActivity>()
        Assert.assertTrue(scenario.state == Lifecycle.State.CREATED)
    }

    @Test
    fun testStateAfterSearchActivityLaunch() {
        val scenario = launchActivity<MainActivity>()
        val activityScenario= scenario.onActivity {
            it.startActivity(Intent(it, SearchListActivity::class.java))
        }
        //need to do research to get pause state
        Assert.assertTrue(scenario.state == activityScenario.state)
    }

    @Test
    fun testViewModelIsYourGrame(){
        val scenario = launchActivity<MainActivity>()
        var vm: ViewModel? = null
        scenario.onActivity {
            vm = it.mainPresenter.getViewModel()
        }
        Assert.assertTrue(vm is YourGrameViewModel)
    }

    @Test
    fun testWebViewVisibilityAfterLoading() {
        val scenario = launchActivity<MainActivity>()
        onView(withId(R.id.enter_url)).perform(clearText(),typeText("https://www.google.com"))
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.webView)).check(matches(isDisplayed()))
    }

    //TODO("Endless testing ")

}