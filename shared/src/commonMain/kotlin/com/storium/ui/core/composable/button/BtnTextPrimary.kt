package com.storium.ui.core.composable.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.containerShapeDefault
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimaryHalf
import org.jetbrains.compose.resources.painterResource

@Composable
fun BtnTextPrimary(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = marginPrimary, vertical = marginPrimary),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val colors = MaterialTheme.appColors
    val currentColor by remember(isPressed, isEnabled, colors) {
        derivedStateOf {
            when {
                !isEnabled -> colors.textDisabled
                isPressed -> colors.textSecondary
                else -> colors.textPrimary
            }
        }
    }

    TextButton(
        modifier = modifier,
        enabled = isEnabled,
        onClick = onClick,
        interactionSource = interactionSource,
        contentPadding = contentPadding,
        shape = containerShapeDefault,
        colors = ButtonDefaults.textButtonColors(
            contentColor = currentColor,
            disabledContentColor = colors.textDisabled,
        ),
    ) {
        leadingIcon?.let {
            Image(
                painter = it,
                contentDescription = null,
                modifier = Modifier.size(defaultIconSize),
            )
            Spacer(modifier = Modifier.width(marginPrimaryHalf))
        }

        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = currentColor,
            maxLines = 1,
        )

        trailingIcon?.let {
            Spacer(modifier = Modifier.width(marginPrimaryHalf))
            Image(
                painter = it,
                contentDescription = null,
                modifier = Modifier.size(defaultIconSize),
            )
        }
    }
}

@Preview(backgroundColor = 0xFFF9F9F9, showBackground = true)
@Composable
private fun BtnTextPrimaryPreview() {
    StoriumTheme {
        BtnTextPrimary(text = "View All", trailingIcon = painterResource(AppIcons.ArrowRight), onClick = {})
    }
}

@Preview(backgroundColor = 0xFFF9F9F9, showBackground = true)
@Composable
private fun BtnTextPrimaryDisabledPreview() {
    StoriumTheme {
        BtnTextPrimary(text = "View All", onClick = {}, isEnabled = false)
    }
}
