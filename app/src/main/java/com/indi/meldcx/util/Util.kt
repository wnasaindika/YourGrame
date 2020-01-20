package com.indi.meldcx.util

import android.content.Context
import com.indi.meldcx.R
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * create image file in internal storage
 * @param baseFolder
 * @param format
 * @param extension
 */
fun createFile(baseFolder: File, format:String, extension:String) = File(baseFolder, SimpleDateFormat(format, Locale.getDefault())
    .format(System.currentTimeMillis()) + extension)

/**
 * delete image file
 * @param file
 */
fun deleteImage(file: File) = file.delete()

/**
 * get time and date
 */
fun getDateTime() = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)).toString()

/**
 * create folder in external media dir
 * @param context
 */
fun getOutputDirectory(context: Context): File = context.let{
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() } }
    if (mediaDir != null && mediaDir.exists())  mediaDir else context.filesDir
}
