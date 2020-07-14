package com.bolddevoteam.adidaschallenge.core.data

import android.graphics.drawable.Drawable
import java.text.SimpleDateFormat
import java.util.*

open class TimelineItem(val name: String,
                        @Transient val icon: Drawable,
                        val _startTime: Long,
                        val _endTime: Long,
                        val type: Int) {

    companion object {
        val TYPE_PHYSICAL_ACTIVITY = 1
        val TYPE_APP_USAGE = 2
    }

    @Transient
    private val formatter = SimpleDateFormat("H:mm:ss a", Locale.getDefault())

    val startTime : String
        get() = formatter.format(_startTime)
    val endTime : String
        get() = formatter.format(_endTime)
}