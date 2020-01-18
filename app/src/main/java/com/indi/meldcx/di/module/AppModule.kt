package com.indi.meldcx.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.indi.meldcx.data.CaptureImageDao
import com.indi.meldcx.data.CaptureImageDatabase
import com.indi.meldcx.ui.list.presenter.ListPresenter
import com.indi.meldcx.ui.list.presenter.ListVMPresenter
import com.indi.meldcx.ui.list.view.SearchListView
import com.indi.meldcx.ui.main.presenter.MainPresenter
import com.indi.meldcx.ui.main.view.MainView
import com.indi.meldcx.ui.main.presenter.Presenter
import dagger.Module
import dagger.Provides


@Module
class AppModule {
    @Provides
    internal fun provideContext(application: Application): Context = application

    @Provides
    fun provideMainPresenter(presenter: Presenter<MainView>): MainPresenter<MainView> = presenter

    @Provides
    fun provideListPresenter(presenter: ListPresenter<SearchListView>): ListVMPresenter<SearchListView> = presenter

    @Provides
    fun provideDatabase(context: Context): CaptureImageDatabase = Room
        .databaseBuilder(context.applicationContext, CaptureImageDatabase::class.java, "MeldCX.db")
        .allowMainThreadQueries()
        .build()

    @Provides
    fun provideCaptureImages(db: CaptureImageDatabase): CaptureImageDao = db.captureImageDao()
}