package com.indi.meldcx.ui.base.presenter

import com.indi.meldcx.ui.base.view.BaseView
import com.indi.meldcx.ui.vm.MeldCXViewModel
import javax.inject.Inject

open class BasePresenter<V : BaseView> @Inject internal constructor(val vm: MeldCXViewModel) :  BaseVMPresenter<V> {

    private var view: V? = null
    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    override fun getView(): V? = view
}