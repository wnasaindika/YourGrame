package com.indi.meldcx.ui.list.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.indi.meldcx.R
import com.indi.meldcx.ui.base.view.BaseActivity
import com.indi.meldcx.ui.base.common.MeldCXUIContainer
import com.indi.meldcx.ui.list.presenter.ListPresenter
import com.indi.meldcx.ui.list.view.adapters.CaptureImageAdapter
import com.indi.meldcx.ui.vm.MeldCXViewModel
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.search_activity_layout.*
import javax.inject.Inject

class SearchListActivity : BaseActivity(), SearchListView,HasAndroidInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var presenter: ListPresenter<SearchListView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity_layout)
        presenter.onAttach(this)
    }
    override fun onFragmentAttached() {}
    override fun onFragmentDettached() {}
    override fun showLoading(): View { TODO("Not Implemented")}
    override fun hideLoading()  {}
    override fun setUpRecyclerView() : CaptureImageAdapter {
        val layerInit = MeldCXUIContainer.instance.setUpRecyclerViewUtility(this)
        recyclerView.layoutManager = layerInit.first
        recyclerView.itemAnimator  = layerInit.second
        recyclerView.addItemDecoration(layerInit.third)
        val adapter = CaptureImageAdapter(this,this)
        recyclerView.adapter = adapter
        return adapter
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}