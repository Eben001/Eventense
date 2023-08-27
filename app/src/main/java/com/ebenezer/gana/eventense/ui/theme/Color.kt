package com.ebenezer.gana.eventense.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val LoadingBlue = Color(0xFF1E62D6)
val ErrorRed = Color(0xFFF44336)
val InfoGreen = Color(0xFF08F320)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val Purple = Color(0xFF9966BD)


val Colors.topAppBarContentColor: Color
    get() = if(isLight) Color.White else Color.LightGray


val Colors.topAppBarBackground:Color
    get() = if(isLight) Purple else Color.Black