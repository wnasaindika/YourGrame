package com.indi.meldcx.ui.main.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.bumptech.glide.Glide
import com.indi.meldcx.R
import com.indi.meldcx.data.CaptureImage
import com.indi.meldcx.ui.base.view.BaseActivity
import com.indi.meldcx.ui.base.common.MeldCXUIContainer
import com.indi.meldcx.ui.list.view.SearchListActivity
import com.indi.meldcx.ui.main.presenter.MainPresenter
import com.indi.meldcx.util.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.main_activity_layout.*
import kotlinx.android.synthetic.main.main_activity_search_view.*
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class MainActivity : BaseActivity(),HasAndroidInjector,
    MainView {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var mainPresenter: MainPresenter<MainView>

    private val onSave = { webView:WebView? -> saveImage(webView) }
    private var currentWebView: WebView? = null
    private var onPageLoaded: (WebView?) -> Unit = { webView:WebView? -> currentWebView = webView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)
        mainPresenter.onAttach(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDetach()
    }
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
    override fun setSearchView(): View = MeldCXUIContainer.instance.addWebSearchView(this, main)
    override fun removeSearchView() = MeldCXUIContainer.instance.removeWebSearchView(main)
    override fun showLoading(): View = MeldCXUIContainer.instance.addProgressView(this, main)
    override fun hideLoading() = MeldCXUIContainer.instance.removeProgressView(main)

    @SuppressLint("SetJavaScriptEnabled")
    override fun loadWebPage() = search.setOnClickListener {
           //clear cache
           webView.clearCache(true)

           webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading()
                hideCaptureImageVisibility(webView)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                hideLoading()
                onPageLoaded.invoke(view)
            }
        }
           webView.settings.javaScriptEnabled = true
           webView.loadUrl(enter_url.text.toString())
    }

    override fun navigateToListView() = image_list.setOnClickListener { startActivityForResult(Intent(this,  SearchListActivity::class.java), RESULT_FROM_SEARCH_LIST_REQUEST_CODE) }

    override fun onSaveImage() = fab.setOnClickListener { onSave.invoke(currentWebView) }

    private fun  saveImage(webView: WebView?) = webView.let {
        val outputDirectory = MeldCXUIContainer.instance.getOutputDirectory(this)
        Toast.makeText(this,getString(R.string.show_saving),Toast.LENGTH_LONG).show()
        it?.apply {
            //it.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED),View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED))
            //it.layout(0,0,it.measuredWidth,it.measuredHeight)
            val bitmap = Bitmap.createBitmap(it.measuredWidth,it.measuredHeight,Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
                canvas.drawBitmap(bitmap,0f,bitmap.height.toFloat(), Paint())
            it.draw(canvas)
            val file = createFile(outputDirectory, DATE_FORMAT, FILE_EXTENSION)
            val fileStream = FileOutputStream(file)
            mainPresenter.insertToCaptureImage(getDateTime(),it.originalUrl,file.absolutePath)
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileStream)
            fileStream.flush()
            fileStream.close()
            setCaptureImageVisibility(it)
            Glide.with(this).load(file).error(R.drawable.ic_icon_cross).into(capturedImage)
        } ?: Toast.makeText(this,getString(R.string.show_error),Toast.LENGTH_LONG).show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RESULT_FROM_SEARCH_LIST_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null){
                if(data.hasExtra(RESULT_CAPTURE_IMAGE_KEY)) {
                    val clickedItem =  data.extras?.get(RESULT_CAPTURE_IMAGE_KEY) as CaptureImage
                    setCaptureImageVisibility(webView)
                    Glide.with(this).load(File(clickedItem.imageLocation)).error(R.drawable.ic_icon_cross).into(capturedImage)
                    enter_url.setText(clickedItem.url)
                    search.performClick()
                }
            }
        }
    }

    private fun setCaptureImageVisibility(webView: WebView) {
        webView.visibility = View.GONE
        capturedImage.visibility = View.VISIBLE
    }

    private fun hideCaptureImageVisibility(webView: WebView) {
        webView.visibility = View.VISIBLE
        capturedImage.visibility = View.GONE
    }

}