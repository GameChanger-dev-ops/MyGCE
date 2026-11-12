package com.mygce.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mygce.app.countdown.countdownFor
import com.mygce.app.data.ExamDateStore
import com.mygce.app.ui.HomeScreen
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val store = ExamDateStore(this)
        setContent {
            var examDate by remember { mutableStateOf(store.load()) }
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(
                        // "Today" is read on each recomposition so a reopened app is never a day stale.
                        countdown = countdownFor(LocalDate.now(), examDate),
                        onExamDateChosen = {
                            store.save(it)
                            examDate = it
                        },
                    )
                }
            }
        }
    }
}
