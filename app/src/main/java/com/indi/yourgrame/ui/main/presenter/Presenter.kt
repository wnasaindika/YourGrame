package com.indi.yourgrame.ui.main.presenter

import com.indi.yourgrame.data.CaptureImage
import com.indi.yourgrame.ui.base.presenter.BasePresenter
import com.indi.yourgrame.ui.main.view.MainView
import com.indi.yourgrame.ui.vm.YourGrameViewModel
import javax.inject.Inject

/**
 * <h1>Presenter</h1>
 * Sub class of BasePresenter, to provide view and functions to MainActivity
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
class Presenter<V : MainView,VM: YourGrameViewModel > @Inject constructor(meldCXViewModel: VM) : BasePresenter<V,VM>(meldCXViewModel), MainPresenter<V,VM> {

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

    override fun insertToCaptureImage(dateTime: String, url: String, imagePath: String) =
        getViewModel().insertImage(
            CaptureImage(
                url = url,
                imageLocation = imagePath,
                capturedTime = dateTime
            )
        )

}