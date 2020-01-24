package com.indi.yourgrame.ui.list.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.indi.yourgrame.R
import com.indi.yourgrame.data.CaptureImage
import com.indi.yourgrame.ui.base.view.BaseActivity
import com.indi.yourgrame.ui.list.presenter.ListPresenter
import com.indi.yourgrame.ui.list.view.adapters.CaptureImageAdapter
import com.indi.yourgrame.ui.vm.YourGrameViewModel
import com.indi.yourgrame.util.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.search.*
import kotlinx.android.synthetic.main.search_activity_layout.*
import javax.inject.Inject

/**
 * <h1>SearchListActivity</h1>
 * List View consist of all captured images from Web View
 * user can delete and filter images or reload the url again
 *
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
class SearchListActivity : BaseActivity(), SearchListView,HasAndroidInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var listPresenter: ListPresenter<SearchListView,YourGrameViewModel>

    private  lateinit var adapter:CaptureImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity_layout)
        listPresenter.onAttach(this)
    }
    //-- append layouts
    override fun showLoading(): View { TODO("Not Implemented")}
    override fun hideLoading()  {}
    override fun addListSearchView(): View = getYourGrameUI().addListSearchView(this,list)
    override fun removeListSearchView()    = getYourGrameUI().removeListSearchView(list)
    //-- append  layouts ends
    /**
     * setting up recycler view
     */
    override fun setUpRecyclerView() : CaptureImageAdapter {
        val layerInit = getYourGrameUI().setUpRecyclerViewUtility(this)
        recyclerView.layoutManager = layerInit.first
        recyclerView.itemAnimator  = layerInit.second
        recyclerView.addItemDecoration(layerInit.third)
        adapter = CaptureImageAdapter(this,this)
        recyclerView.adapter = adapter
        return adapter
    }

    /**
     * perform search on image list (when user type search)
     * @param adapter Capture Image adapter
     */
    override fun performSearch(adapter: CaptureImageAdapter?) = search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(p0: String?): Boolean = false
        override fun onQueryTextChange(text: String?): Boolean {
            adapter?.filter(text ?: "")
            return false
        }

    })

    /**
     * reload url on Main Activity
     * @param item CaptureImage object
     */
    override fun onItemClick(item: CaptureImage) = setResult(Activity.RESULT_OK, Intent()
        .apply { this.putExtra(
        RESULT_CAPTURE_IMAGE_KEY,item) } ).also { finish() }

    /**
     * Perform delete
     * @param item CaptureImage
     */
    override fun onDeleteClick(item: CaptureImage) {
        search_view.setQuery("",false)
        showMessage(getString(R.string.show_on_delete,item.url))
        listPresenter.onDeleteItem(item)
    }

    /**
     * show error toast
     */
    override fun showMessage(error: String) = Toast.makeText(this,error,Toast.LENGTH_LONG).show()

    /**
     * reset list
     */
    override fun onSearchTextClear(adapter: CaptureImageAdapter?,list: LiveData<List<CaptureImage>>) = reset.setOnClickListener {
        search_view.setQuery("",false)
        adapter?.setList(list)
    }
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}