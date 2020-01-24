package com.indi.yourgrame.di.module

import android.app.Application
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room
import com.indi.yourgrame.data.CaptureImageDao
import com.indi.yourgrame.data.CaptureImageDatabase
import com.indi.yourgrame.ui.base.common.YourGrameUIContainer
import com.indi.yourgrame.ui.list.presenter.ListPresenter
import com.indi.yourgrame.ui.list.presenter.ListVMPresenter
import com.indi.yourgrame.ui.list.view.SearchListView
import com.indi.yourgrame.ui.main.presenter.MainPresenter
import com.indi.yourgrame.ui.main.view.MainView
import com.indi.yourgrame.ui.main.presenter.Presenter
import com.indi.yourgrame.ui.vm.YourGrameViewModel
import com.indi.yourgrame.util.DB_NAME
import dagger.Module
import dagger.Provides

/**
 * <h1>AppModule</h1>
 * Adding YourGrame App dependencies to AppModule.
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
    fun provideMainPresenter(presenter: Presenter<MainView,YourGrameViewModel>): MainPresenter<MainView,YourGrameViewModel> = presenter

    @Provides
    fun provideListPresenter(presenter: ListPresenter<SearchListView, YourGrameViewModel>): ListVMPresenter<SearchListView,YourGrameViewModel> = presenter

    @Provides
    fun provideDatabase(context: Context): CaptureImageDatabase = Room
        .databaseBuilder(context.applicationContext, CaptureImageDatabase::class.java, DB_NAME)
        .allowMainThreadQueries()
        .build()

    @Provides
    fun provideCaptureImages(db: CaptureImageDatabase): CaptureImageDao = db.captureImageDao()

    @Provides
    fun provideYourGrameUI(): YourGrameUIContainer<ConstraintLayout> = YourGrameUIContainer.instance
}