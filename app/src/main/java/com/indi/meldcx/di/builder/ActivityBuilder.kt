package com.indi.meldcx.di.builder

import com.indi.meldcx.ui.list.view.SearchListActivity
import com.indi.meldcx.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [])
    abstract fun provideMainActivity(): MainActivity
    @ContributesAndroidInjector(modules = [])
    abstract fun provideSearchListActivity(): SearchListActivity
}