package com.loyds.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF000000),
    secondary = Color(0xFFff6b00),
    tertiary = Color(0xFFFFFBFE),
    background = BackgroundDark,
    surface = SurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onPrimary = Color(0xFFFFFBFE),
    onSecondary = Color.White,
    onBackground = Color(0xFFFFFBFE),
    onSurface = OnSurfaceDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    outlineVariant = Color(0x33d3d3d3),
    primaryContainer = Color.White,
    onPrimaryContainer = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1e1e1e),
    secondary = Color(0xFFff6b00),
    tertiary = Color(0xFF1e1e1e),
    background = Color(0xFFFFFFFF),
    surface = Color.White,
    surfaceVariant = Color(0xFFFFFBFE),
    onBackground = Color.Black,
    onSecondary = Color.White,
    onSurface = Color.Black,
    onSurfaceVariant = Color.Black,
    outlineVariant = Color.White,
    primaryContainer = Color.Black,
    onPrimaryContainer = Color.White
)

object NewsTheme {
    val dimens: NewsDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current
}

val LocalDimens: ProvidableCompositionLocal<NewsDimens> = staticCompositionLocalOf {
    throw wrapException
}

val wrapException = ExceptionInInitializerError("Please wrap composable in NewsTheme")

@Composable
fun NewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalDimens provides NewsDimens(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}