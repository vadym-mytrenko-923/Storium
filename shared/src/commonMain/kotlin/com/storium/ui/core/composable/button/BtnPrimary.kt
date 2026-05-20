package com.storium.ui.core.composable.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors

@Composable
fun BtnPrimary(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    text: String,
    leadingContent: @Composable () -> Unit = {},
    trailingContent: @Composable () -> Unit = {},
    tintLeadingContent: Boolean = true,
    tintTrailingContent: Boolean = true,
) {
    BaseButton(
        containerColor = MaterialTheme.appColors.btnPrimary,
        contentColor = MaterialTheme.appColors.btnPrimaryText,
        pressedContainerColor = MaterialTheme.appColors.btnPrimaryPressed,
        disabledContainerColor = MaterialTheme.appColors.btnPrimaryDisabled,
        disabledContentColor = MaterialTheme.appColors.btnPrimaryTextDisabled,
        modifier = modifier,
        isEnabled = isEnabled,
        applyShadow = true,
        shadowColor = MaterialTheme.appColors.btnPrimaryShadow,
        onClick = onClick,
        textComponent = {
            Text(
                text = text.uppercase(),
                color = if (isEnabled) {
                    MaterialTheme.appColors.btnPrimaryText
                } else {
                    MaterialTheme.appColors.btnPrimaryTextDisabled
                },
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
            )
        },
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        tintLeadingContent = tintLeadingContent,
        tintTrailingContent = tintTrailingContent,
    )
}

@Preview
@Composable
private fun BtnPrimaryPreview() {
    StoriumTheme {
        BtnPrimary(modifier = Modifier.fillMaxWidth(), text = "Add to Cart", onClick = {})
    }
}

@Preview
@Composable
private fun BtnPrimaryDisabledPreview() {
    StoriumTheme {
        BtnPrimary(modifier = Modifier.fillMaxWidth(), text = "Add to Cart", onClick = {}, isEnabled = false)
    }
}
