package com.indi.yourgrame.ui.list.view

import android.view.View
import androidx.lifecycle.LiveData
import com.indi.yourgrame.data.CaptureImage
import com.indi.yourgrame.ui.base.view.BaseView
import com.indi.yourgrame.ui.list.view.adapters.CaptureImageAdapter

/**
 * <h1>SearchListView</h1>
 *  interface SearchListView
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
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