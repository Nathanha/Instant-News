package com.nathan.instant_news

import com.nathan.instant_news.utils.Utils
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 * Test Utils class methods
 */
class UtilsTest {

    /**
     * Test getFormattedDateForDisplay method with 0 minute difference
     */
    @Test
    fun getFormattedDateForDisplayTestNow() {
        val formattedDate = Utils.getFormattedDateForDisplay(Date())
        assertEquals("Il y a 0 minute", formattedDate)
    }

    /**
     * Test getFormattedDateForDisplay method with 10 minutes difference
     */
    @Test
    fun getFormattedDateForDisplayTestMinutes() {
        val dateTime: LocalDateTime = LocalDateTime.now().minusMinutes(10)
        val date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant())

        val formattedDate = Utils.getFormattedDateForDisplay(date)
        assertEquals("Il y a 10 minutes", formattedDate)
    }

    /**
     * Test getFormattedDateForDisplay method with 1 hour difference
     */
    @Test
    fun getFormattedDateForDisplayTestHour() {
        val dateTime: LocalDateTime = LocalDateTime.now().minusHours(1)
        val date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant())

        val formattedDate = Utils.getFormattedDateForDisplay(date)
        assertEquals("Il y a 1 heure", formattedDate)
    }

    /**
     * Test getFormattedDateForDisplay method with 3 hours difference
     */
    @Test
    fun getFormattedDateForDisplayTestHours() {
        val dateTime: LocalDateTime = LocalDateTime.now().minusHours(3)
        val date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant())

        val formattedDate = Utils.getFormattedDateForDisplay(date)
        assertEquals("Il y a 3 heures", formattedDate)
    }

    /**
     * Test getFormattedDateForDisplay method with 1 day difference
     */
    @Test
    fun getFormattedDateForDisplayTestDay() {
        val dateTime: LocalDateTime = LocalDateTime.now().minusDays(1)
        val date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant())

        val formattedDate = Utils.getFormattedDateForDisplay(date)
        assertEquals("Il y a 1 jour", formattedDate)
    }

    /**
     * Test getFormattedDateForDisplay method with 4 days difference
     */
    @Test
    fun getFormattedDateForDisplayTestDays() {
        val dateTime: LocalDateTime = LocalDateTime.now().minusDays(4)
        val date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant())

        val formattedDate = Utils.getFormattedDateForDisplay(date)
        assertEquals("Il y a 4 jours", formattedDate)
    }
}