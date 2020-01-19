package com.indi.meldcx.ui.list.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.indi.meldcx.R
import com.indi.meldcx.data.CaptureImage
import com.indi.meldcx.ui.base.view.BaseActivity
import com.indi.meldcx.ui.base.common.MeldCXUIContainer
import com.indi.meldcx.ui.list.presenter.ListPresenter
import com.indi.meldcx.ui.list.view.adapters.CaptureImageAdapter
import com.indi.meldcx.util.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.search.*
import kotlinx.android.synthetic.main.search_activity_layout.*
import javax.inject.Inject

class SearchListActivity : BaseActivity(), SearchListView,HasAndroidInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var presenter: ListPresenter<SearchListView>

    private  lateinit var adapter:CaptureImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity_layout)
        presenter.onAttach(this)
    }
    override fun showLoading(): View { TODO("Not Implemented")}
    override fun hideLoading()  {}
    override fun setUpRecyclerView() : CaptureImageAdapter {
        val layerInit = MeldCXUIContainer.instance.setUpRecyclerViewUtility(this)
        recyclerView.layoutManager = layerInit.first
        recyclerView.itemAnimator  = layerInit.second
        recyclerView.addItemDecoration(layerInit.third)
        adapter = CaptureImageAdapter(this,this)
        recyclerView.adapter = adapter
        return adapter
    }

    override fun addListSearchView(): View = MeldCXUIContainer.instance.addListSearchView(this,list)
    override fun removeListSearchView() = MeldCXUIContainer.instance.removeListSearchView(list)
    override fun performSearch(adapter: CaptureImageAdapter?) = search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(p0: String?): Boolean = false
        override fun onQueryTextChange(text: String?): Boolean {
            adapter?.filter(text ?: "")
            return false
        }

    })
    override fun onItemClick(item: CaptureImage) = setResult(Activity.RESULT_OK, Intent()
        .apply { this.putExtra(
        RESULT_CAPTURE_IMAGE_KEY,item) } ).also { finish() }

    override fun onDeleteClick(item: CaptureImage) {
        search_view.setQuery("",false)
        showMessage(getString(R.string.show_on_delete,item.url))
        presenter.onDeleteItem(item)
    }
    override fun showMessage(error: String) = Toast.makeText(this,error,Toast.LENGTH_LONG).show()
    override fun onSearchTextClear(adapter: CaptureImageAdapter?,list: LiveData<List<CaptureImage>>) = reset.setOnClickListener {
        search_view.setQuery("",false)
        adapter?.setList(list)
    }
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}