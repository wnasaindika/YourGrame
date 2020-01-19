package com.indi.meldcx.ui.list.presenter

import com.indi.meldcx.data.CaptureImage
import com.indi.meldcx.ui.base.presenter.BaseVMPresenter
import com.indi.meldcx.ui.list.view.SearchListView

interface ListVMPresenter <V: SearchListView> : BaseVMPresenter<V> {
  fun onDeleteItem(captureImage: CaptureImage)
}