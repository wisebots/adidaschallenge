package com.bolddevoteam.adidaschallenge.core.data

import android.graphics.drawable.Drawable

class AppUsage(appName : String,
               appIcon : Drawable,
               startTime : Long,
               endTime : Long) : TimelineItem(appName, appIcon, startTime, endTime, TYPE_APP_USAGE)