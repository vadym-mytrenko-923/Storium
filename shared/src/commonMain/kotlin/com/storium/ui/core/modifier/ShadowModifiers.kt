package com.storium.ui.core.modifier

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimaryHalf

@Composable
expect fun Modifier.shadowGlow(
    color: Color,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = marginPrimary,
    offsetX: Dp = 0.dp,
    offsetY: Dp = marginPrimaryHalf,
    spread: Dp = 0.dp,
): Modifier
