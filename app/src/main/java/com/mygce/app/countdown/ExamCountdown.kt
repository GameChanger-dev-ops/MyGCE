package com.mygce.app.countdown

import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

/** What the home screen shows for the exam date. Pure Kotlin so it is unit-tested on the JVM. */
sealed interface Countdown {
    data object NotSet : Countdown
    data class Upcoming(val days: Long) : Countdown
    data object Today : Countdown
    data class Passed(val daysAgo: Long) : Countdown
}

/**
 * Whole calendar days between [today] and [exam]. Dates (not timestamps) are compared on purpose:
 * a student sees "1 day left" the evening before, regardless of the hour.
 */
fun countdownFor(today: LocalDate, exam: LocalDate?): Countdown {
    if (exam == null) return Countdown.NotSet
    val days = ChronoUnit.DAYS.between(today, exam)
    return when {
        days > 0 -> Countdown.Upcoming(days)
        days == 0L -> Countdown.Today
        else -> Countdown.Passed(-days)
    }
}

/** Reads a stored ISO date (yyyy-MM-dd). Bad or missing data means "not set", never a crash. */
fun parseExamDate(stored: String?): LocalDate? {
    if (stored.isNullOrBlank()) return null
    return try {
        LocalDate.parse(stored)
    } catch (_: DateTimeParseException) {
        null
    }
}
