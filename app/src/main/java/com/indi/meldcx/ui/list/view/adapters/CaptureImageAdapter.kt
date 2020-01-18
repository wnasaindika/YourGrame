package com.indi.meldcx.ui.list.view.adapters

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indi.meldcx.R
import com.indi.meldcx.data.CaptureImage
import kotlinx.android.synthetic.main.list_row.view.*
import java.io.File

class CaptureImageAdapter(val context: Context,val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<CaptureImageAdapter.ViewHolder>() {

    private var list: List<CaptureImage> = emptyList()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.list_row,false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.url.text = list[position].url
        Glide.with(context).load(File(list[position].imageLocation)).error(R.drawable.ic_icon_cross).into(holder.itemView.image)

    }

    fun setList(liveList:  LiveData<List<CaptureImage>>) {
        liveList.observe(lifecycleOwner, Observer {
            list = it
        })
        notifyDataSetChanged()
    }
    /**
     * extension function to view holder/group
     */
    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}