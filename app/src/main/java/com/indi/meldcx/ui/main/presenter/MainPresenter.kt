package com.indi.meldcx.ui.main.presenter

import com.indi.meldcx.ui.base.presenter.BaseVMPresenter
import com.indi.meldcx.ui.main.view.MainView
import com.indi.meldcx.ui.vm.MeldCXViewModel

/**
 * <h1>MainPresenter</h1>
 * interface MainPresenter
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
interface MainPresenter<V: MainView,VM: MeldCXViewModel> : BaseVMPresenter<V,VM>{
    fun insertToCaptureImage(dateTime: String,url: String,imagePath: String)
}