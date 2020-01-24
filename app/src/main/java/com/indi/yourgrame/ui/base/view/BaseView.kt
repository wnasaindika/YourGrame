package com.indi.yourgrame.ui.base.view

import androidx.constraintlayout.widget.ConstraintLayout
import com.indi.yourgrame.ui.base.common.YourGrameUIContainer

/**
 * <h1>BaseView</h1>
 *  BaseView interface
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
interface BaseView {
   fun getYourGrameUI(): YourGrameUIContainer<ConstraintLayout>
}