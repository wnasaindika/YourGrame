package com.indi.yourgrame.ui.vm

import androidx.lifecycle.ViewModel
import com.indi.yourgrame.data.CaptureImage
import com.indi.yourgrame.data.CaptureImageDao
import javax.inject.Inject

/**
 * <h1>YourGrameViewModel</h1>
 * View Models for YourGrame
 * provide all utility facilities (non UI) across the application
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
open class YourGrameViewModel @Inject internal constructor(private val captureImageDao: CaptureImageDao): ViewModel(){
    val images = captureImageDao.getAllImages()
    fun deleteImage(id:String) = captureImageDao.deleteImage(id)
    fun insertImage(captureImage: CaptureImage) = captureImageDao.insertImages(captureImage)
}