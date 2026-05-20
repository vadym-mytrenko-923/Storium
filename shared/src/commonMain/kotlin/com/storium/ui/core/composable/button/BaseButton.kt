package com.storium.ui.core.composable.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.storium.ui.core.modifier.modifyIf
import com.storium.ui.core.modifier.shadowGlow
import com.storium.ui.theme.appColors
import com.storium.ui.theme.btnCornerRadius
import com.storium.ui.theme.buttonHeight
import com.storium.ui.theme.buttonShapeDefault
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.marginPrimaryHalf

@Composable
fun BaseButton(
    modifier: Modifier,
    isEnabled: Boolean,
    containerColor: Color,
    contentColor: Color,
    pressedContainerColor: Color,
    disabledContainerColor: Color,
    disabledContentColor: Color,
    onClick: () -> Unit,
    textComponent: @Composable () -> Unit = {},
    leadingContent: @Composable () -> Unit = {},
    trailingContent: @Composable () -> Unit = {},
    tintLeadingContent: Boolean = true,
    tintTrailingContent: Boolean = true,
    applyShadow: Boolean = false,
    shadowColor: Color = MaterialTheme.appColors.btnPrimaryShadow,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentContainerColor by remember(isPressed, isEnabled, containerColor, pressedContainerColor) {
        derivedStateOf {
            if (isEnabled && isPressed) pressedContainerColor else containerColor
        }
    }

    Button(
        modifier = modifier
            .height(buttonHeight)
            .modifyIf(applyShadow && isEnabled && !isPressed) {
                shadowGlow(
                    color = shadowColor,
                    blurRadius = marginPrimary2X,
                    offsetY = marginPrimaryHalf,
                    borderRadius = btnCornerRadius,
                )
            },
        enabled = isEnabled,
        shape = buttonShapeDefault,
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = currentContainerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        ),
        onClick = onClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(marginPrimary),
        ) {
            if (tintLeadingContent) {
                leadingContent()
            } else {
                CompositionLocalProvider(LocalContentColor provides Color.Unspecified) {
                    leadingContent()
                }
            }

            textComponent()

            if (tintTrailingContent) {
                trailingContent()
            } else {
                CompositionLocalProvider(LocalContentColor provides Color.Unspecified) {
                    trailingContent()
                }
            }
        }
    }
}
