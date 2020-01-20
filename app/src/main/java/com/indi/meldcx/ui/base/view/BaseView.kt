package com.indi.meldcx.ui.base.view

import androidx.constraintlayout.widget.ConstraintLayout
import com.indi.meldcx.ui.base.common.MeldCXUIContainer

/**
 * <h1>BaseView</h1>
 *  BaseView interface
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
interface BaseView {
   fun getMeldCXUI(): MeldCXUIContainer<ConstraintLayout>
}