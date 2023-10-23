package com.rasyidin.notepal.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    const val DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"

    fun currentDateTime(): String {
        val sdf = SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT, Locale.getDefault())
        return sdf.format(Date())
    }

    fun String.toFormatStringDate(
        toFormat: String,
        fromFormat: String = DEFAULT_DATE_TIME_FORMAT,
    ): String {
        return try {
            val local = SimpleDateFormat(fromFormat, Locale.getDefault())
            val formater = SimpleDateFormat(toFormat, Locale.getDefault())
            val parse = local.parse(this)
            if (parse != null) {
                formater.format(parse)
            } else {
                throw IllegalArgumentException("it should have a value, it's null instead")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            this
        }
    }
}