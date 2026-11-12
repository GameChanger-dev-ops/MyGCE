package com.mygce.app.countdown

import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ExamCountdownTest {
    private val today = LocalDate.of(2027, 5, 10)

    @Test fun `no exam date means not set`() {
        assertEquals(Countdown.NotSet, countdownFor(today, null))
    }

    @Test fun `future date counts whole days`() {
        assertEquals(Countdown.Upcoming(1), countdownFor(today, LocalDate.of(2027, 5, 11)))
        assertEquals(Countdown.Upcoming(21), countdownFor(today, LocalDate.of(2027, 5, 31)))
    }

    @Test fun `same day is today`() {
        assertEquals(Countdown.Today, countdownFor(today, today))
    }

    @Test fun `past date reports days ago`() {
        assertEquals(Countdown.Passed(3), countdownFor(today, LocalDate.of(2027, 5, 7)))
    }

    @Test fun `counts across month and leap year boundaries`() {
        assertEquals(Countdown.Upcoming(2), countdownFor(LocalDate.of(2028, 2, 28), LocalDate.of(2028, 3, 1)))
        assertEquals(Countdown.Upcoming(1), countdownFor(LocalDate.of(2027, 2, 28), LocalDate.of(2027, 3, 1)))
        assertEquals(Countdown.Upcoming(366), countdownFor(LocalDate.of(2027, 12, 31), LocalDate.of(2028, 12, 31)))
    }

    @Test fun `parse accepts a valid stored date`() {
        assertEquals(LocalDate.of(2027, 6, 1), parseExamDate("2027-06-01"))
    }

    @Test fun `parse treats missing or corrupt data as not set`() {
        assertNull(parseExamDate(null))
        assertNull(parseExamDate(""))
        assertNull(parseExamDate("   "))
        assertNull(parseExamDate("01/06/2027"))
        assertNull(parseExamDate("2027-13-45"))
    }
}
