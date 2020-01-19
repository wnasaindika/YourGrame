package com.indi.meldcx.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*
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