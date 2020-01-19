package com.indi.meldcx.ui.base.common

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.indi.meldcx.R
import java.io.File

/**
 * singleton MeldCX UI container
 */
class MeldCXUIContainer {

    fun addWebSearchView(context: Context, container: ConstraintLayout): View =  View.inflate(context,R.layout.main_activity_search_view,container)
    fun removeWebSearchView(container: ConstraintLayout)   =  container.findViewById<ConstraintLayout>(R.id.search_bar).let { container.removeView(it) }
    fun addProgressView(context: Context, container: ConstraintLayout): View   =  View.inflate(context,R.layout.progress,container)
    fun removeProgressView(container: ConstraintLayout) =  container.findViewById<ConstraintLayout>(R.id.progress_bar).let { container.removeView(it) }
    fun removeListSearchView(container: ConstraintLayout)   =  container.findViewById<ConstraintLayout>(R.id.search_bar).let { container.removeView(it) }
    fun addListSearchView(context: Context, container: ConstraintLayout): View  =  View.inflate(context,R.layout.search,container)

    fun getOutputDirectory(context: Context): File = context.let{
        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
            File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() } }
        if (mediaDir != null && mediaDir.exists())  mediaDir else context.filesDir
    }

    fun setUpRecyclerViewUtility(context: Context): Triple<LinearLayoutManager, DefaultItemAnimator, DividerItemDecoration> {
        val layoutManager   = LinearLayoutManager(context)
        val itemAnimator    = DefaultItemAnimator   ()
        val divider         = DividerItemDecoration (context, layoutManager.orientation)
        return Triple(layoutManager,itemAnimator,divider)
    }

    private object MeldCXUI {
        val INSTANCE = MeldCXUIContainer()
    }

    companion object {
        val instance by lazy { MeldCXUI.INSTANCE }
    }
}