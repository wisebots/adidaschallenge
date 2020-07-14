package com.bolddevoteam.adidaschallenge.core.data

import android.graphics.drawable.Drawable

class PhysicalMoves(name : String,
                    icon : Drawable,
                    startTime : Long,
                    endTime : Long) : TimelineItem(name, icon, startTime, endTime, TYPE_PHYSICAL_ACTIVITY)