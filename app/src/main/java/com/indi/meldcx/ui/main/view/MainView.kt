package com.indi.meldcx.ui.main.view

import android.view.View
import com.indi.meldcx.ui.base.view.BaseView

interface MainView : BaseView {
    fun setSearchView(): View
    fun removeSearchView()
    fun loadWebPage()
    fun navigateToListView()
    fun onSaveImage()
}