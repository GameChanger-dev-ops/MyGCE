package com.mygce.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mygce.app.R
import com.mygce.app.countdown.Countdown
import com.mygce.app.subjects.Subject
import com.mygce.app.ui.brand.MyGceMark
import com.mygce.app.ui.theme.AccentBiology
import com.mygce.app.ui.theme.AccentChemistry
import com.mygce.app.ui.theme.AccentEnglish
import com.mygce.app.ui.theme.AccentMaths
import com.mygce.app.ui.theme.BrandBlueDark
import com.mygce.app.ui.theme.BrandBlueLight
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun HomeScreen(
    countdown: Countdown,
    examDate: LocalDate?,
    onExamDateChosen: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showPicker by remember { mutableStateOf(false) }
    var shown by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { shown = true }

    Box(modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                // Targeting Android 15 draws the app edge to edge: keep content clear of bars and cutouts.
                .safeDrawingPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Header()
            Staggered(shown, 0) {
                CountdownHero(countdown, examDate, onChangeClick = { showPicker = true })
            }
            Text(
                stringResource(R.string.subjects_heading),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Subject.entries.chunked(2).forEachIndexed { row, pair ->
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    pair.forEachIndexed { col, subject ->
                        Staggered(shown, 1 + row * 2 + col, Modifier.weight(1f)) { SubjectCard(subject) }
                    }
                }
            }
        }
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
private fun Header() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        MyGceMark(size = 48.dp)
        Spacer(Modifier.width(14.dp))
        Column {
            Text(
                stringResource(R.string.home_title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                stringResource(R.string.home_tagline),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

/** Fades and slides each block in, one after another, so the first screen feels alive but calm. */
@Composable
private fun Staggered(visible: Boolean, index: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val delay = index * 70
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn(tween(350, delayMillis = delay)) +
            slideInVertically(tween(350, delayMillis = delay)) { it / 8 },
    ) { content() }
}

@Composable
private fun CountdownHero(countdown: Countdown, examDate: LocalDate?, onChangeClick: () -> Unit) {
    val resources = LocalContext.current.resources
    val shape = RoundedCornerShape(28.dp)
    val onHero = Color.White
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(Brush.linearGradient(listOf(BrandBlueLight, BrandBlueDark)))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        when (countdown) {
            Countdown.NotSet -> {
                Text(stringResource(R.string.countdown_not_set_title), style = MaterialTheme.typography.headlineSmall, color = onHero)
                Text(stringResource(R.string.countdown_not_set_body), style = MaterialTheme.typography.bodyLarge, color = onHero.copy(alpha = 0.85f))
            }
            Countdown.Today -> {
                Text(stringResource(R.string.countdown_today_title), fontSize = 44.sp, fontWeight = FontWeight.Bold, color = onHero)
                Text(stringResource(R.string.countdown_today_body), style = MaterialTheme.typography.bodyLarge, color = onHero.copy(alpha = 0.85f))
            }
            is Countdown.Upcoming -> HeroNumber(
                number = countdown.days.toString(),
                label = resources.getQuantityString(R.plurals.countdown_label_upcoming, countdown.days.toInt()),
            )
            is Countdown.Passed -> HeroNumber(
                number = countdown.daysAgo.toString(),
                label = resources.getQuantityString(R.plurals.countdown_label_passed, countdown.daysAgo.toInt()),
            )
        }
        if (examDate != null) {
            Text(
                stringResource(R.string.exam_date_on, examDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))),
                style = MaterialTheme.typography.labelLarge,
                color = onHero.copy(alpha = 0.9f),
            )
        }
        Text(stringResource(R.string.exam_date_hint), style = MaterialTheme.typography.bodySmall, color = onHero.copy(alpha = 0.75f))
        Spacer(Modifier.size(4.dp))
        FilledTonalButton(
            onClick = onChangeClick,
            colors = ButtonDefaults.filledTonalButtonColors(containerColor = onHero.copy(alpha = 0.2f), contentColor = onHero),
        ) {
            val label = if (examDate == null) R.string.exam_date_set else R.string.exam_date_change
            Text(stringResource(label))
        }
    }
}

@Composable
private fun HeroNumber(number: String, label: String) {
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(number, fontSize = 72.sp, fontWeight = FontWeight.Bold, color = Color.White, lineHeight = 76.sp)
        Text(
            label,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.padding(bottom = 12.dp),
        )
    }
}

@Composable
private fun SubjectCard(subject: Subject) {
    val (nameRes, initialsRes, accent) = when (subject) {
        Subject.MATHEMATICS -> Triple(R.string.subject_mathematics, R.string.subject_initials_mathematics, AccentMaths)
        Subject.ENGLISH_LANGUAGE -> Triple(R.string.subject_english_language, R.string.subject_initials_english_language, AccentEnglish)
        Subject.BIOLOGY -> Triple(R.string.subject_biology, R.string.subject_initials_biology, AccentBiology)
        Subject.CHEMISTRY -> Triple(R.string.subject_chemistry, R.string.subject_initials_chemistry, AccentChemistry)
    }
    Card(
        modifier = Modifier.fillMaxWidth().heightIn(min = 132.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = Modifier.size(44.dp).clip(RoundedCornerShape(14.dp)).background(accent.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center,
            ) {
                Text(stringResource(initialsRes), style = MaterialTheme.typography.titleMedium, color = accent)
            }
            Text(stringResource(nameRes), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            // Honest placeholder: no content exists yet (content rights come first, DECISIONS/0001).
            Text(
                stringResource(R.string.subject_coming_soon),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
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
