package com.indi.meldcx.util

import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


fun createFile(baseFolder: File, format:String, extension:String) = File(baseFolder, SimpleDateFormat(format, Locale.getDefault())
    .format(System.currentTimeMillis()) + extension)
fun deleteImage(file: File) = file.delete()
fun getDateTime() = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)).toString()