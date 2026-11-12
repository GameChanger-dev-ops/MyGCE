package com.mygce.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mygce.app.R
import com.mygce.app.countdown.Countdown
import com.mygce.app.subjects.Subject
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

@Composable
fun HomeScreen(
    countdown: Countdown,
    onExamDateChosen: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showPicker by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(stringResource(R.string.home_title), style = MaterialTheme.typography.headlineLarge)
        Text(stringResource(R.string.home_tagline), style = MaterialTheme.typography.bodyLarge)

        CountdownCard(countdown = countdown, onChangeClick = { showPicker = true })

        Text(stringResource(R.string.subjects_heading), style = MaterialTheme.typography.titleMedium)
        Subject.entries.forEach { SubjectCard(it) }
    }

    if (showPicker) {
        ExamDatePickerDialog(
            onDismiss = { showPicker = false },
            onConfirm = {
                showPicker = false
                onExamDateChosen(it)
            },
        )
    }
}

@Composable
private fun CountdownCard(countdown: Countdown, onChangeClick: () -> Unit) {
    val resources = LocalContext.current.resources
    val headline = when (countdown) {
        Countdown.NotSet -> stringResource(R.string.countdown_not_set)
        Countdown.Today -> stringResource(R.string.countdown_today)
        is Countdown.Upcoming ->
            resources.getQuantityString(R.plurals.countdown_upcoming, countdown.days.toInt(), countdown.days)
        is Countdown.Passed ->
            resources.getQuantityString(R.plurals.countdown_passed, countdown.daysAgo.toInt(), countdown.daysAgo)
    }
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(headline, style = MaterialTheme.typography.titleLarge)
            Text(stringResource(R.string.exam_date_hint), style = MaterialTheme.typography.bodySmall)
            Button(onClick = onChangeClick) {
                val label = if (countdown == Countdown.NotSet) R.string.exam_date_set else R.string.exam_date_change
                Text(stringResource(label))
            }
        }
    }
}

@Composable
private fun SubjectCard(subject: Subject) {
    val nameRes = when (subject) {
        Subject.MATHEMATICS -> R.string.subject_mathematics
        Subject.ENGLISH_LANGUAGE -> R.string.subject_english_language
        Subject.BIOLOGY -> R.string.subject_biology
        Subject.CHEMISTRY -> R.string.subject_chemistry
    }
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(stringResource(nameRes), style = MaterialTheme.typography.titleMedium)
            // Honest placeholder: no content exists yet (content rights come first, DECISIONS/0001).
            Text(stringResource(R.string.subject_coming_soon), style = MaterialTheme.typography.bodySmall)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExamDatePickerDialog(onDismiss: () -> Unit, onConfirm: (LocalDate) -> Unit) {
    val state = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                enabled = state.selectedDateMillis != null,
                onClick = {
                    // The picker returns midnight UTC of the chosen day; read it back as a UTC date.
                    state.selectedDateMillis?.let {
                        onConfirm(Instant.ofEpochMilli(it).atZone(ZoneOffset.UTC).toLocalDate())
                    }
                },
            ) { Text(stringResource(R.string.dialog_ok)) }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text(stringResource(R.string.dialog_cancel)) } },
    ) {
        DatePicker(state = state)
    }
}
