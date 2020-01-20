package com.indi.meldcx.ui.base.presenter

import androidx.lifecycle.ViewModel
import com.indi.meldcx.ui.base.view.BaseView
import com.indi.meldcx.ui.vm.MeldCXViewModel
import javax.inject.Inject
/**
 * <h1>BasePresenter</h1>
 * Implementation of BaseVMPresenter, to provide view and view model during the UI visibility
 * Can have common views and functions
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
open class BasePresenter<V : BaseView,VM: ViewModel> @Inject internal constructor(val vm: VM) :  BaseVMPresenter<V,VM> {

    private var view: V? = null
    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    override fun getView(): V? = view
    override fun getViewModel(): VM = vm

}