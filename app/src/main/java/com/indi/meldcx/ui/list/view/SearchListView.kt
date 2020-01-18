package com.indi.meldcx.ui.list.view

import com.indi.meldcx.ui.base.view.BaseView
import com.indi.meldcx.ui.list.view.adapters.CaptureImageAdapter

interface SearchListView : BaseView{
 fun setUpRecyclerView(): CaptureImageAdapter
}