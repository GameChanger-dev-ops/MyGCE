package com.mygce.app

import android.graphics.Color as AndroidColor
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.mygce.app.countdown.countdownFor
import com.mygce.app.data.ExamDateStore
import com.mygce.app.ui.HomeScreen
import com.mygce.app.ui.brand.StudioSplash
import com.mygce.app.ui.theme.MyGceTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val store = ExamDateStore(this)
        // Respect "remove animations": no studio animation if the system animation scale is 0.
        val animationsOn = Settings.Global.getFloat(contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f) > 0f
        setContent {
            var showSplash by rememberSaveable { mutableStateOf(animationsOn) }
            var examDate by remember { mutableStateOf(store.load()) }

            // The splash is always dark, so its status bar icons must be light whatever the phone theme is.
            LaunchedEffect(showSplash) {
                if (showSplash) {
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.dark(AndroidColor.TRANSPARENT),
                        navigationBarStyle = SystemBarStyle.dark(AndroidColor.TRANSPARENT),
                    )
                } else {
                    enableEdgeToEdge()
                }
            }

            MyGceTheme {
                Crossfade(targetState = showSplash, animationSpec = tween(450), label = "splash") { splash ->
                    if (splash) {
                        StudioSplash(onFinished = { showSplash = false })
                    } else {
                        HomeScreen(
                            // "Today" is read on each recomposition so a reopened app is never a day stale.
                            countdown = countdownFor(LocalDate.now(), examDate),
                            examDate = examDate,
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
}
