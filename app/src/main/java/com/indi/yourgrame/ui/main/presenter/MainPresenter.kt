package com.indi.yourgrame.ui.main.presenter

import com.indi.yourgrame.ui.base.presenter.BaseVMPresenter
import com.indi.yourgrame.ui.main.view.MainView
import com.indi.yourgrame.ui.vm.YourGrameViewModel

/**
 * <h1>MainPresenter</h1>
 * interface MainPresenter
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
interface MainPresenter<V: MainView,VM: YourGrameViewModel> : BaseVMPresenter<V,VM>{
    fun insertToCaptureImage(dateTime: String,url: String,imagePath: String)
}