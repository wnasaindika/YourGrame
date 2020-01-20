package com.indi.meldcx.di.module

import android.app.Application
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room
import com.indi.meldcx.data.CaptureImageDao
import com.indi.meldcx.data.CaptureImageDatabase
import com.indi.meldcx.ui.base.common.MeldCXUIContainer
import com.indi.meldcx.ui.list.presenter.ListPresenter
import com.indi.meldcx.ui.list.presenter.ListVMPresenter
import com.indi.meldcx.ui.list.view.SearchListView
import com.indi.meldcx.ui.main.presenter.MainPresenter
import com.indi.meldcx.ui.main.view.MainView
import com.indi.meldcx.ui.main.presenter.Presenter
import com.indi.meldcx.ui.vm.MeldCXViewModel
import com.indi.meldcx.util.DB_NAME
import dagger.Module
import dagger.Provides

/**
 * <h1>AppModule</h1>
 * Adding MeldCX App dependencies to AppModule.
 * this class contains presenters, databases and meldCX UIs
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
@Module
class AppModule {
    @Provides
    internal fun provideContext(application: Application): Context = application

    @Provides
    fun provideMainPresenter(presenter: Presenter<MainView,MeldCXViewModel>): MainPresenter<MainView,MeldCXViewModel> = presenter

    @Provides
    fun provideListPresenter(presenter: ListPresenter<SearchListView, MeldCXViewModel>): ListVMPresenter<SearchListView,MeldCXViewModel> = presenter

    @Provides
    fun provideDatabase(context: Context): CaptureImageDatabase = Room
        .databaseBuilder(context.applicationContext, CaptureImageDatabase::class.java, DB_NAME)
        .allowMainThreadQueries()
        .build()

    @Provides
    fun provideCaptureImages(db: CaptureImageDatabase): CaptureImageDao = db.captureImageDao()

    @Provides
    fun provideMeldCXUI(): MeldCXUIContainer<ConstraintLayout> = MeldCXUIContainer.instance
}