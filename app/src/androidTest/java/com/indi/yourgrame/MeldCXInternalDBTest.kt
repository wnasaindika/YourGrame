package com.indi.yourgrame

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.indi.yourgrame.data.CaptureImage
import com.indi.yourgrame.data.CaptureImageDatabase
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before

/**
 * Instrumented test,
 * Testing YourGrame data base
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class YourGrameInternalDBTest {

    private lateinit var captureImageDatabase: CaptureImageDatabase
    private  lateinit var fakeCaptureImage: CaptureImage
    @Before
    fun setUp() {
        captureImageDatabase = Room
            .inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().context,
                CaptureImageDatabase::class.java
            )
            .build()
        fakeCaptureImage = CaptureImage("1","https://google.com","sdk/media/image.png","2020-01-20 20:10 1000")
    }

    @After
    fun closeDb() {
        captureImageDatabase.close()
    }

    @Test
    fun insetCaptureImage(){
        captureImageDatabase.captureImageDao().insertImages(fakeCaptureImage)
        val liveData= captureImageDatabase.captureImageDao().getAllImages()
        assert(liveData.value?.isNotEmpty() ?: false)
    }

    @Test
    fun deleteCaptureImage(){
        captureImageDatabase.captureImageDao().insertImages(fakeCaptureImage)
        val liveData= captureImageDatabase.captureImageDao().getAllImages()
        assert(liveData.value?.isNotEmpty() ?: false)
        captureImageDatabase.captureImageDao().deleteImage(liveData.value?.first()?.id ?: "0")
        assert(liveData.value?.isEmpty() ?: true)
    }

}
