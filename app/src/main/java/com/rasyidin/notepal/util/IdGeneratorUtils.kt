package com.rasyidin.notepal.util

object IdGeneratorUtils {
    private val alphanumeric = ('A'..'Z') + ('a'..'z') + ('0'..'9')

    const val PREFIX_ID_CONTENT_FREE_TEXT = "FT"
    const val PREFIX_ID_CONTENT_IMAGE = "IMG"
    const val PREFIX_ID_CONTENT_TASK = "TSK"
    const val PREFIX_ID_CONTENT_SUB_TASK = "STSK"
    const val PREFIX_ID_NOTE = "NOTE"
    fun generateId(
        prefix: String,
        length: Int = 10
    ) = buildString {
        append(prefix)
        append("/")
        repeat(length) {
            append(alphanumeric.random().uppercase())
        }
    }
}