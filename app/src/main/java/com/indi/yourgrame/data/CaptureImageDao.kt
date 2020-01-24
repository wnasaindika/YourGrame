package com.indi.yourgrame.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * <h1>CaptureImageDao</h1>
 * @see Dao class to create, delete and insert captured images.
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
@Dao
interface CaptureImageDao {

    @Query("SELECT * FROM images")
    fun getAllImages(): LiveData<List<CaptureImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(captureImage: CaptureImage)

    @Query("DELETE FROM images where id = :id")
    fun deleteImage(id: String)
}