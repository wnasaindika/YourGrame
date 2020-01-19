package com.indi.meldcx.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.indi.meldcx.R
import com.indi.meldcx.ui.base.view.BaseActivity
import com.indi.meldcx.ui.base.common.MeldCXUIContainer
import com.indi.meldcx.ui.list.view.SearchListActivity
import com.indi.meldcx.ui.main.presenter.MainPresenter
import com.indi.meldcx.util.DATE_FORMAT
import com.indi.meldcx.util.FILE_EXTENSION
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.main_activity_layout.*
import kotlinx.android.synthetic.main.main_activity_search_view.*
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
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

    override fun onFragmentAttached() {}
    override fun onFragmentDettached() {}
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
    override fun setSearchView(): View = MeldCXUIContainer.instance.addWebSearchView(this, main)
    override fun removeSearchView() = MeldCXUIContainer.instance.removeWebSearchView(main)
    override fun showLoading(): View = MeldCXUIContainer.instance.addProgressView(this, main)
    override fun hideLoading() = MeldCXUIContainer.instance.removeProgressView(main)

    @SuppressLint("SetJavaScriptEnabled")
    override fun loadWebPage() = search.setOnClickListener {
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading()
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

    override fun navigateToListView() = image_list.setOnClickListener {  startActivity(Intent(this,  SearchListActivity::class.java)) }

    override fun onSaveImage() = fab.setOnClickListener { onSave.invoke(currentWebView) }

    private fun  saveImage(webView: WebView?) = webView.let {
        val outputDirectory = MeldCXUIContainer.instance.getOutputDirectory(this)
        it?.apply {
            //it.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED),View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED))
            //it.layout(0,0,it.measuredWidth,it.measuredHeight)
            val bitmap = Bitmap.createBitmap(it.measuredWidth,it.measuredHeight,Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
                canvas.drawBitmap(bitmap,0f,bitmap.height.toFloat(), Paint())
            it.draw(canvas)
            val file = createFile(outputDirectory, DATE_FORMAT, FILE_EXTENSION)
            val fileStream = FileOutputStream(file)
            mainPresenter.insertToCaptureImage(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)).toString(),it.originalUrl,file.absolutePath)
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileStream)
            fileStream.flush()
            fileStream.close()
        } ?: Toast.makeText(this,"Shit happen",Toast.LENGTH_LONG).show()

    }

    private fun createFile(baseFolder:File, format:String,extension:String) = File(baseFolder, SimpleDateFormat(format, Locale.US)
        .format(System.currentTimeMillis()) + extension)
}