package com.indi.meldcx.ui.base.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.indi.meldcx.ui.base.view.BaseActivity
import dagger.android.support.AndroidSupportInjection
//TODO("not use in this project")
class BaseFragment : Fragment(){

    lateinit var baseActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity){
            baseActivity = context
            baseActivity.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performSupportDI()
        setHasOptionsMenu(false)
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity.onFragmentAttached()
    }

    private fun performSupportDI() = AndroidSupportInjection.inject(this)

    interface CallBack{
        fun onFragmentAttached()
        fun onFragmentDettached()
    }
}