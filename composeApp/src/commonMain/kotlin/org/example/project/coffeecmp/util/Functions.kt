package org.example.project.coffeecmp.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import coffeecmp.composeapp.generated.resources.Res
import coffeecmp.composeapp.generated.resources.bebasneue
import coffeecmp.composeapp.generated.resources.djbcoffee
import org.jetbrains.compose.resources.Font
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import coffeecmp.composeapp.generated.resources.oswald_light
import coffeecmp.composeapp.generated.resources.oswald_regular
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

//Bounce Click Effect
enum class ButtonState { Pressed, Idle }
fun Modifier.bounceClick() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0.80f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {  }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}

//DateTime Formatter
fun formatLocalDateTime(localDateTime: LocalDateTime): String {
    val day = localDateTime.dayOfMonth.toString().padStart(2, '0')
    val month = localDateTime.monthNumber.toString().padStart(2, '0')
    val year = localDateTime.year.toString()
    return "$day/$month/$year"
}

val datetimeInSystemZone: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

val formattedDate = formatLocalDateTime(datetimeInSystemZone)

//Custom fonts
@Composable
fun GetBebasFontFamily() = FontFamily(Font(Res.font.bebasneue))

@Composable
fun GetDjbCoffeeFontFamily() = FontFamily(Font(Res.font.djbcoffee))

@Composable
fun GetOswaldFontLightFamily() = FontFamily(Font(Res.font.oswald_light))

@Composable
fun GetOswaldFontRegularFamily() = FontFamily(Font(Res.font.oswald_regular))