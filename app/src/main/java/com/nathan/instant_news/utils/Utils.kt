package com.nathan.instant_news.utils

import java.util.*

class Utils {
    companion object {
        /**
         * Get the formatted number of minutes, hours or days between now and the date param
         */
        fun getFormattedDateForDisplay(date: Date): String {
            val now = Date()
            val diff: Long = now.time - date.time
            val minutes = (diff / (1000 * 60))
            val hours = minutes/60
            val days = hours/24
            return when {
                days > 0 -> {
                    "Il y a $days " + (if (days > 1) "jours" else "jour")
                }
                hours > 0 -> {
                    "Il y a $hours " + (if (hours > 1) "heures" else "heure")
                }
                else -> {
                    "Il y a $minutes " + (if (minutes > 1) "minutes" else "minute")
                }
            }
        }
    }
}