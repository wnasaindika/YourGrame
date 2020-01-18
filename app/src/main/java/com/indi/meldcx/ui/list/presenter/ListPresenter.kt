package com.indi.meldcx.ui.list.presenter

import com.indi.meldcx.ui.base.presenter.BasePresenter
import com.indi.meldcx.ui.list.view.SearchListView
import com.indi.meldcx.ui.vm.MeldCXViewModel
import javax.inject.Inject

class ListPresenter<V : SearchListView> @Inject internal constructor(private val viewModel: MeldCXViewModel) : BasePresenter<V>(viewModel),ListVMPresenter<V> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        getView()?.setUpRecyclerView().apply {
            this?.setList(viewModel.images)
        }

    }

    override fun onDetach() {
        super.onDetach()
    }
}