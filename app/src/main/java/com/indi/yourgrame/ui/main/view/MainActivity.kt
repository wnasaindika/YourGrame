package com.indi.yourgrame.ui.main.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.bumptech.glide.Glide
import com.indi.yourgrame.R
import com.indi.yourgrame.data.CaptureImage
import com.indi.yourgrame.ui.base.view.BaseActivity
import com.indi.yourgrame.ui.list.view.SearchListActivity
import com.indi.yourgrame.ui.main.presenter.MainPresenter
import com.indi.yourgrame.ui.vm.YourGrameViewModel
import com.indi.yourgrame.util.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.main_activity_layout.*
import kotlinx.android.synthetic.main.main_activity_search_view.*
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

/**
 * <h1>MainActivity</h1>
 * Entry point to application, this allow you to search any website and download its content to web view
 * user allows to capture image and user can save the image with its details
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
class MainActivity : BaseActivity(), HasAndroidInjector, MainView {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var mainPresenter: MainPresenter<MainView,YourGrameViewModel>

    private val onSave = { webView: WebView? -> saveImage(webView) }
    /** when each time user want to new web content. this method trigger after page loading*/
    private var onPageLoaded: (WebView?) -> Unit = { webView: WebView? -> currentWebView = webView }
    /** temp web view holder*/
    private var currentWebView: WebView? = null

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
    //-- append layouts
    override fun setSearchView(): View = getYourGrameUI().addWebSearchView(this, main)
    override fun removeSearchView()    = getYourGrameUI().removeWebSearchView(main)
    override fun showLoading(): View   = getYourGrameUI().addProgressView(this, main)
    override fun hideLoading()         = getYourGrameUI().removeProgressView(main)
    //-- append layouts ends

    /**
     * load web page when user click on search
     */
    @SuppressLint("SetJavaScriptEnabled")
    override fun loadWebPage() = search.setOnClickListener {
        //clear cache
        webView.clearCache(true)

        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading()
                setCaptureImageVisibility(false)
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

    /**
     * navigate to List view when user click list icon
     */
    override fun navigateToListView() = image_list.setOnClickListener {
        startActivityForResult(
            Intent(this,SearchListActivity::class.java),
            RESULT_FROM_SEARCH_LIST_REQUEST_CODE
        )
    }

    /**
     * save image when fab button click
     */
    override fun onSaveImage() = fab.setOnClickListener { onSave.invoke(currentWebView) }

    /**
     * process and save image
     * @param webView WebView object
     */
    private fun saveImage(webView: WebView?) = webView.let {
        val outputDirectory = getOutputDirectory(this)
        Toast.makeText(this, getString(R.string.show_saving), Toast.LENGTH_LONG).show()
        it?.apply {
            val bitmap =
                Bitmap.createBitmap(it.measuredWidth, it.measuredHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawBitmap(bitmap, 0f, bitmap.height.toFloat(), Paint())
            it.draw(canvas)
            val file = createFile(outputDirectory, DATE_FORMAT, FILE_EXTENSION)
            val fileStream = FileOutputStream(file)
            mainPresenter.insertToCaptureImage(getDateTime(), it.originalUrl, file.absolutePath)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileStream)
            fileStream.flush()
            fileStream.close()
            setCaptureImageVisibility(true)
            Glide.with(this).load(file).error(R.drawable.ic_icon_cross).into(capturedImage)
        } ?: Toast.makeText(this, getString(R.string.show_error), Toast.LENGTH_LONG).show()

    }

    /**
     * listing to Search List Activity results
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_FROM_SEARCH_LIST_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                if (data.hasExtra(RESULT_CAPTURE_IMAGE_KEY)) {
                    val clickedItem = data.extras?.get(RESULT_CAPTURE_IMAGE_KEY) as CaptureImage
                    setCaptureImageVisibility(true)
                    Glide.with(this).load(File(clickedItem.imageLocation))
                        .error(R.drawable.ic_icon_cross).into(capturedImage)
                    enter_url.setText(clickedItem.url)
                    search.performClick()
                }
            }
        }
    }

    /**
     * control web view and image view visibility programmatically
     * @param isHide flag tru for image visibility
     */
    private fun setCaptureImageVisibility(isHide: Boolean) = when (isHide) {
        true -> {
            webView.visibility = View.GONE
            capturedImage.visibility = View.VISIBLE
        }
        false -> {
            webView.visibility = View.VISIBLE
            capturedImage.visibility = View.GONE
        }
    }

}