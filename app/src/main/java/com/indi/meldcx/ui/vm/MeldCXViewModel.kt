package com.indi.meldcx.ui.vm

import androidx.lifecycle.ViewModel
import com.indi.meldcx.data.CaptureImage
import com.indi.meldcx.data.CaptureImageDao
import javax.inject.Inject

/**
 * <h1>MeldCXViewModel</h1>
 * View Models for MeldCX
 * provide all utility facilities (non UI) across the application
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
open class MeldCXViewModel @Inject internal constructor(private val captureImageDao: CaptureImageDao): ViewModel(){
    val images = captureImageDao.getAllImages()
    fun deleteImage(id:String) = captureImageDao.deleteImage(id)
    fun insertImage(captureImage: CaptureImage) = captureImageDao.insertImages(captureImage)
}