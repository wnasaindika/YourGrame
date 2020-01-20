package com.indi.meldcx.di.builder

import com.indi.meldcx.ui.list.view.SearchListActivity
import com.indi.meldcx.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * <h1>ActivityBuilder</h1>
 * Building activity dependency injections
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [])
    abstract fun provideMainActivity(): MainActivity
    @ContributesAndroidInjector(modules = [])
    abstract fun provideSearchListActivity(): SearchListActivity
}