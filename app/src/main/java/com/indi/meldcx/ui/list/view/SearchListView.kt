package com.indi.meldcx.ui.list.view

import android.view.View
import androidx.lifecycle.LiveData
import com.indi.meldcx.data.CaptureImage
import com.indi.meldcx.ui.base.view.BaseView
import com.indi.meldcx.ui.list.view.adapters.CaptureImageAdapter

interface SearchListView : BaseView{
 fun setUpRecyclerView(): CaptureImageAdapter
 fun addListSearchView(): View
 fun removeListSearchView()
 fun performSearch(adapter: CaptureImageAdapter?)
 fun onSearchTextClear(adapter: CaptureImageAdapter?,list: LiveData<List<CaptureImage>>)
 fun onItemClick(item: CaptureImage)
 fun onDeleteClick(item: CaptureImage)
 fun showMessage(error: String)
}