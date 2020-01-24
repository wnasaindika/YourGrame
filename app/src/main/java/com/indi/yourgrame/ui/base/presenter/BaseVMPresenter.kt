package com.indi.yourgrame.ui.base.presenter

import androidx.lifecycle.ViewModel
import com.indi.yourgrame.ui.base.view.BaseView
/**
 * <h1>BaseVMPresenter</h1>
 * interface BaseVMPresenter
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
interface BaseVMPresenter<V : BaseView,VM: ViewModel> {
    fun onAttach(view: V?)
    fun onDetach()
    fun getView(): V?
    fun getViewModel(): VM
}