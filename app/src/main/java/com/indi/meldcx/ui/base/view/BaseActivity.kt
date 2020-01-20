package com.indi.meldcx.ui.base.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.indi.meldcx.ui.base.common.MeldCXUIContainer
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * <h1>BaseActivity</h1>
 * BaseActivity extends AppCompatActivity to provide MeldCX of Activity,
 * The subclass of BaseActivity consist of shared MeldCX behaviour i.e full screen mode and MeldCX Utility
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */

abstract class BaseActivity : AppCompatActivity(),BaseView {
    @Inject
    lateinit var meldCX: MeldCXUIContainer<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }
    private fun performDI() = AndroidInjection.inject(this)

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus) hideSystemUI()
    }

    override fun getMeldCXUI(): MeldCXUIContainer<ConstraintLayout> = meldCX
    abstract fun showLoading(): View
    abstract fun hideLoading()
}