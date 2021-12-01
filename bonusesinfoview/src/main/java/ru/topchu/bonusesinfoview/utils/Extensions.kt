package ru.topchu.bonusesinfoview.utils

import android.icu.text.SimpleDateFormat

object Extensions {
    fun String.formatDate(): String? {
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM")
        return formatter.format(parser.parse(this))
    }
}