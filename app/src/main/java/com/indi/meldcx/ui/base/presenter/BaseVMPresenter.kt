package com.indi.meldcx.ui.base.presenter

import com.indi.meldcx.ui.base.view.BaseView

interface BaseVMPresenter<V : BaseView> {
    fun onAttach(view: V?)
    fun onDetach()
    fun getView(): V?
}