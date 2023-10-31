package com.example.tonwalletapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = Colors(
    background = Color(0xFFFFFFFF),
    primaryTitle = Color(0xff222222),
    secondPrimaryTitle = Color(0xFF000000),
    secondBackground = Color(0xFF000000),
    primaryBackground = Color(0xFF339CEC),
    btnTitle = Color(0xFFFFFFFF),
    btnBackground = Color(0xFFF0F0F0),
    btnSubtitle = Color(0xFFA8A8A8),
    strokeFormColor = Color(0xFF757575),
    buttonColor = Color(0xFFF0F0F0),
    thirdBackground = Color(0xFF000000),
    secondFormColor = Color(0xFFF1F1F4),
    thirdPrimaryTitle = Color(0xFF37A818),
    fourthPrimaryTitle = Color(0xFFFE3C30),
    alertBackground = Color(0xFF2F373F)
)


private val LightColorPalette = Colors(
    background = Color(0xFFFFFFFF),
    primaryTitle = Color(0xff222222),
    secondPrimaryTitle = Color(0xFF000000),
    secondBackground = Color(0xFF000000),
    primaryBackground = Color(0xFF339CEC),
    btnTitle = Color(0xFFFFFFFF),
    btnBackground = Color(0xFFF0F0F0),
    btnSubtitle = Color(0xFFA8A8A8),
    strokeFormColor = Color(0xFF757575),
    buttonColor = Color(0xFFF0F0F0),
    thirdBackground = Color(0xFF000000),
    secondFormColor = Color(0xFFF1F1F4),
    thirdPrimaryTitle = Color(0xFF37A818),
    fourthPrimaryTitle = Color(0xFFFE3C30),
    alertBackground = Color(0xE62F373F)
)


@Composable
fun TonWalletAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalColorProvider provides LightColorPalette
    ) {
        content()
    }
}

data class Colors(
    val background: Color,
    val primaryTitle:Color,
    val secondPrimaryTitle:Color,
    val secondBackground:Color,
    val primaryBackground:Color,
    val btnTitle:Color,
    val btnBackground:Color,
    val btnSubtitle:Color,
    val strokeFormColor:Color,
    val buttonColor:Color,
    val thirdBackground:Color,
    val secondFormColor:Color,
    val thirdPrimaryTitle:Color,
    val fourthPrimaryTitle:Color,
    val alertBackground:Color
)

object AppTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current
}

val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("fddfdd")
}