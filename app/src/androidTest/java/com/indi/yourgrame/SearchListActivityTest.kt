package com.indi.yourgrame

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.indi.yourgrame.ui.list.view.SearchListActivity
import com.indi.yourgrame.ui.vm.YourGrameViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchListActivityTest {

    @Test
    fun testActivityCreated() {
        val scenario = launchActivity<SearchListActivity>()
        Assert.assertTrue(scenario.state == Lifecycle.State.RESUMED)
    }

    @Test
    fun testViewModelIsYourGrame(){
        val scenario = launchActivity<SearchListActivity>()
        var vm: ViewModel? = null
        scenario.onActivity {
            vm = it.listPresenter.getViewModel()
        }
        Assert.assertTrue(vm is YourGrameViewModel)
    }

    //TODO("Endless testing for search activity")
}