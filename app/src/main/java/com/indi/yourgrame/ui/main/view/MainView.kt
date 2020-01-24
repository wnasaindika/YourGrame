package com.indi.yourgrame.ui.main.view

import android.view.View
import com.indi.yourgrame.ui.base.view.BaseView
/**
 * <h1>MainView</h1>
 * interface MainView
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
interface MainView : BaseView {
    fun setSearchView(): View
    fun removeSearchView()
    fun loadWebPage()
    fun navigateToListView()
    fun onSaveImage()
}