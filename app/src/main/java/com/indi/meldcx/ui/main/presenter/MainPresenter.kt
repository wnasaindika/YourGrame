package com.indi.meldcx.ui.main.presenter

import com.indi.meldcx.ui.base.presenter.BaseVMPresenter
import com.indi.meldcx.ui.main.view.MainView

interface MainPresenter<V: MainView> : BaseVMPresenter<V>{
    fun insertToCaptureImage(dateTime: String,url: String,imagePath: String)
}