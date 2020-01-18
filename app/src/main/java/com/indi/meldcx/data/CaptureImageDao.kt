package com.indi.meldcx.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CaptureImageDao {

    @Query("SELECT * FROM images")
    fun getAllImages(): LiveData<List<CaptureImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(captureImage: CaptureImage)

    @Query("DELETE FROM images where id = :id")
    fun deleteImage(id: String)
}