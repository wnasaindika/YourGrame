package com.indi.yourgrame.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * <h1>CaptureImageDatabase</h1>
 * create SQLite databases.
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */

@Database(entities = [CaptureImage::class],version = 1)
abstract class CaptureImageDatabase : RoomDatabase() {
    abstract fun captureImageDao(): CaptureImageDao
}