package com.indi.meldcx.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CaptureImage::class],version = 1)
abstract class CaptureImageDatabase : RoomDatabase() {
    abstract fun captureImageDao(): CaptureImageDao
}