package com.rax.googlenews.core.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
var dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)

/**
 * Created by Rax on 02/04/20.
 */

fun String.formatPublishedDate(): String? {
    return try {
        format.parse(this)
            ?.let {
                dateFormat.format(it)
            }
    } catch (e: ParseException) {
        null
    }
}