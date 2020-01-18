package com.indi.meldcx.ui.vm

import androidx.lifecycle.ViewModel
import com.indi.meldcx.data.CaptureImage
import com.indi.meldcx.data.CaptureImageDao
import javax.inject.Inject

class MeldCXViewModel @Inject internal constructor(private val captureImageDao: CaptureImageDao): ViewModel(){
    val images = captureImageDao.getAllImages()
    fun deleteImage(id:String) = captureImageDao.deleteImage(id)
    fun insertImage(captureImage: CaptureImage) = captureImageDao.insertImages(captureImage)
}