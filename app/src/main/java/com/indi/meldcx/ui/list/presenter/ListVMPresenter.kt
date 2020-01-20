package com.indi.meldcx.ui.list.presenter

import androidx.lifecycle.ViewModel
import com.indi.meldcx.data.CaptureImage
import com.indi.meldcx.ui.base.presenter.BaseVMPresenter
import com.indi.meldcx.ui.list.view.SearchListView

/**
 * <h1>ListVMPresenter</h1>
 * interface ListVMPresenter
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
interface ListVMPresenter <V: SearchListView,VM: ViewModel> : BaseVMPresenter<V,VM> {
  fun onDeleteItem(captureImage: CaptureImage)
}