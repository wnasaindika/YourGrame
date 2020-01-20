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
 * <h1>MeldCXUIContainer</h1>
 * Common class to organize search views, loading views and some UI helpers.
 * User can dynamically add and remove layouts to their main view (ConstraintLayout)
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
class MeldCXUIContainer<T : ConstraintLayout>{

    fun addWebSearchView(context: Context, container: T): View =  View.inflate(context,R.layout.main_activity_search_view,container)
    fun removeWebSearchView(container: T)   =  container.findViewById<T>(R.id.search_bar).let { container.removeView(it) }
    fun addProgressView(context: Context, container: T): View   =  View.inflate(context,R.layout.progress,container)
    fun removeProgressView(container: T) =  container.findViewById<T>(R.id.progress_bar).let { container.removeView(it) }
    fun removeListSearchView(container: T)   =  container.findViewById<T>(R.id.search_bar).let { container.removeView(it) }
    fun addListSearchView(context: Context, container: T): View  =  View.inflate(context,R.layout.search,container)

    fun setUpRecyclerViewUtility(context: Context): Triple<LinearLayoutManager, DefaultItemAnimator, DividerItemDecoration> {
        val layoutManager   = LinearLayoutManager(context)
        val itemAnimator    = DefaultItemAnimator   ()
        val divider         = DividerItemDecoration (context, layoutManager.orientation)
        return Triple(layoutManager,itemAnimator,divider)
    }

    /**
     * creating single tone object
     * If user want to use this object where dependency injections unavailable
     */
    private object MeldCXUI {
        val INSTANCE = MeldCXUIContainer<ConstraintLayout>()
    }

    companion object {
        val instance by lazy { MeldCXUI.INSTANCE }
    }
}