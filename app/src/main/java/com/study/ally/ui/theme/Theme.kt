package com.study.ally.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PastelColors = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,

    secondary = Secondary,
    onSecondary = OnSecondary,

    background = Background,
    onBackground = OnBackground,

    surface = Surface,
    onSurface = OnSurface,

    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,

    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimary,

    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondary,

    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,

    outline = Outline
)

@Composable
fun AllyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colorScheme = PastelColors,
        typography = Typography,
        content = content
    )
}