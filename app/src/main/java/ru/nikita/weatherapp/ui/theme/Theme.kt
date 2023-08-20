package ru.nikita.weatherapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


val LightColors = lightColorScheme(
    primary = light_primary,
    onPrimary = light_onPrimary,
    background = light_background,
    primaryContainer = light_onBackground
)


val DarkColors = darkColorScheme(
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    background = dark_background,
    primaryContainer = dark_onBackground
)

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    var colors: ColorScheme = LightColors
    val view = LocalView.current
    val activity = (view.context as Activity)
    if (!view.isInEditMode) {
        // Убрать экшен бар в API 34
        activity.actionBar?.hide()

        if (!useDarkTheme) {
            colors = LightColors
            SideEffect {
                activity.window.statusBarColor = LightColors.background.toArgb()
                WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars = true
            }
        } else {
            colors = DarkColors
            SideEffect {
                // Статус бар в цвет фона
                activity.window.statusBarColor = colors.background.toArgb()
                // Белые символы в статус баре в темной теме
                WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars = false
            }
        }

    }


    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}