package com.mygce.app.data

import android.content.Context
import com.mygce.app.countdown.parseExamDate
import java.time.LocalDate

/** Keeps the exam date the student typed in, on the device only (no account, no network). */
class ExamDateStore(context: Context) {
    private val prefs = context.applicationContext.getSharedPreferences("mygce_prefs", Context.MODE_PRIVATE)

    fun load(): LocalDate? = parseExamDate(prefs.getString(KEY_EXAM_DATE, null))

    fun save(date: LocalDate) {
        prefs.edit().putString(KEY_EXAM_DATE, date.toString()).apply()
    }

    private companion object {
        const val KEY_EXAM_DATE = "exam_date"
    }
}
