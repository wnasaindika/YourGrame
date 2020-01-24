package com.indi.yourgrame.ui.list.presenter

import com.indi.yourgrame.data.CaptureImage
import com.indi.yourgrame.ui.base.presenter.BasePresenter
import com.indi.yourgrame.ui.list.view.SearchListView
import com.indi.yourgrame.ui.vm.YourGrameViewModel
import com.indi.yourgrame.util.deleteImage
import java.io.File
import javax.inject.Inject

/**
 * <h1>ListPresenter</h1>
 * Sub class of BasePresenter, to provide view and functions to List Activity
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
class ListPresenter<V : SearchListView,VM: YourGrameViewModel> @Inject internal constructor(viewModel: VM) :
    BasePresenter<V,VM>(viewModel), ListVMPresenter<V,VM> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        // init views and adapter
        val adapter = getView()?.setUpRecyclerView().apply {
            this?.setList(getViewModel().images)
        }
        getView()?.addListSearchView()
        getView()?.performSearch(adapter)
        getView()?.onSearchTextClear(adapter, getViewModel().images)
    }

    override fun onDetach() {
        super.onDetach()
        getView()?.removeListSearchView()
    }

    /**
     * Delete item and its image from the app
     * @param captureImage Capture Image Object
     */
    override fun onDeleteItem(captureImage: CaptureImage) = getViewModel()
        .deleteImage(getViewModel().images.value?.find { it.id == captureImage.id }?.id ?: "")
        .also { deleteImage(File(captureImage.imageLocation)) }
}