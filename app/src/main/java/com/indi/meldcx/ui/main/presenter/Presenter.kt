package com.indi.meldcx.ui.main.presenter

import com.indi.meldcx.data.CaptureImage
import com.indi.meldcx.ui.base.presenter.BasePresenter
import com.indi.meldcx.ui.main.view.MainView
import com.indi.meldcx.ui.vm.MeldCXViewModel
import javax.inject.Inject


class Presenter<V : MainView> @Inject constructor(val meldCXViewModel: MeldCXViewModel) : BasePresenter<V>(meldCXViewModel), MainPresenter<V> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        getView()?.setSearchView()
        getView()?.loadWebPage()
        getView()?.navigateToListView()
        getView()?.onSaveImage()
    }

    override fun onDetach() {
        super.onDetach()
        getView()?.removeSearchView()
    }

    override fun insertToCaptureImage(dateTime: String, url: String, imagePath: String) = meldCXViewModel.insertImage(CaptureImage(url = url, imageLocation = imagePath, capturedTime = dateTime))
}