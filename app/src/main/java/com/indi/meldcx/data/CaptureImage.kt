package com.indi.meldcx.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*
/**
 * <h1>CaptureImage</h1>
 * Parcelize data class.
 * Save image data to <b>images</b> table
 *
 * @author  Indika Kumara
 * @version 1.0
 * @since   2020-01-18
 */
@Parcelize
@Entity(tableName = "images")
data class CaptureImage(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "location")
    val imageLocation: String,
    @ColumnInfo(name= "captured_time")
    val capturedTime: String
) : Parcelable