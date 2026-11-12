package com.mygce.app.ui.brand

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mygce.app.R
import com.mygce.app.ui.theme.BrandBlue
import com.mygce.app.ui.theme.BrandRed
import com.mygce.app.ui.theme.Ink
import com.mygce.app.ui.theme.Outfit
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Game Changer Studios animation (logo option C, two tilted tiles). About 2.2 s; tap to skip.
 * Skipped entirely when the system animation scale is 0 (see MainActivity).
 */
@Composable
fun StudioSplash(onFinished: () -> Unit, modifier: Modifier = Modifier) {
    val gIn = remember { Animatable(0f) }
    val cIn = remember { Animatable(0f) }
    val textIn = remember { Animatable(0f) }
    val finish by rememberUpdatedState(onFinished)
    val studioName = stringResource(R.string.studio_full_name)

    LaunchedEffect(Unit) {
        val bounce = spring<Float>(dampingRatio = 0.55f, stiffness = Spring.StiffnessLow)
        launch { gIn.animateTo(1f, bounce) }
        delay(160)
        launch { cIn.animateTo(1f, bounce) }
        delay(420)
        textIn.animateTo(1f, tween(durationMillis = 500))
        delay(1100)
        finish()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Ink)
            .semantics { contentDescription = studioName }
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { finish() },
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Box(Modifier.width(150.dp).height(100.dp)) {
                StudioTile(
                    letter = "G",
                    color = BrandBlue,
                    ring = Color.Transparent,
                    modifier = Modifier
                        .offset(x = 0.dp, y = 8.dp)
                        .graphicsLayer {
                            translationX = (1f - gIn.value) * -160.dp.toPx()
                            rotationZ = -34f + 26f * gIn.value
                            alpha = gIn.value.coerceIn(0f, 1f)
                        },
                )
                StudioTile(
                    letter = "C",
                    color = BrandRed,
                    ring = Ink,
                    modifier = Modifier
                        .offset(x = 62.dp, y = 0.dp)
                        .graphicsLayer {
                            translationX = (1f - cIn.value) * 160.dp.toPx()
                            rotationZ = 30f - 23f * cIn.value
                            alpha = cIn.value.coerceIn(0f, 1f)
                        },
                )
            }
            Box(Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.studio_word),
                color = Color.White,
                fontFamily = Outfit,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                modifier = Modifier.alpha(textIn.value.coerceIn(0f, 1f)).offset(y = ((1f - textIn.value) * 10f).dp),
            )
            Text(
                text = stringResource(R.string.studio_tagline),
                color = Color(0xFFA9B2C0),
                fontFamily = Outfit,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                letterSpacing = 3.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textIn.value.coerceIn(0f, 1f)),
            )
        }
    }
}

@Composable
private fun StudioTile(letter: String, color: Color, ring: Color, modifier: Modifier = Modifier, size: Dp = 88.dp) {
    val shape = RoundedCornerShape(size * 0.26f)
    Box(
        modifier = modifier.size(size).background(color, shape).border(4.dp, ring, shape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = letter,
            color = Color.White,
            fontFamily = Outfit,
            fontWeight = FontWeight.Bold,
            fontSize = (size.value * 0.62f).sp,
        )
    }
}
