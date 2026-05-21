package com.storium.ui.core.composable.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.elevationInput
import com.storium.ui.theme.inputHeight
import com.storium.ui.theme.marginPrimaryHalf
import com.storium.ui.theme.textFieldShapeDefault

@Composable
fun TextFieldPrimary(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorText: String? = null,
    isEnabled: Boolean = true,
    label: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val hasText = value.isNotEmpty()

    Column(modifier = modifier) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(inputHeight)
                .shadow(
                    elevation = elevationInput,
                    shape = textFieldShapeDefault,
                    ambientColor = MaterialTheme.appColors.cardShadow,
                    spotColor = MaterialTheme.appColors.cardShadow,
                ),
            singleLine = true,
            enabled = isEnabled,
            isError = isError,
            shape = textFieldShapeDefault,
            visualTransformation = visualTransformation,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            label = label.takeIf { it.isNotEmpty() }?.let {
                {
                    Text(
                        text = label,
                        style = if (hasText) MaterialTheme.typography.bodySmall else MaterialTheme.typography.labelLarge,
                        color = if (isError) MaterialTheme.appColors.error else MaterialTheme.appColors.textSecondary,
                    )
                }
            },
            textStyle = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.appColors.textInput,
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.appColors.inputBackground,
                unfocusedContainerColor = MaterialTheme.appColors.inputBackground,
                disabledContainerColor = MaterialTheme.appColors.inputBackground,
                errorContainerColor = MaterialTheme.appColors.inputBackground,
                cursorColor = MaterialTheme.appColors.primary,
                errorCursorColor = MaterialTheme.appColors.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
        )

        if (isError && errorText != null) {
            Text(
                text = errorText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.appColors.error,
                modifier = Modifier.padding(start = marginPrimaryHalf, top = marginPrimaryHalf),
            )
        }
    }
}

@Preview
@Composable
private fun TextFieldPrimaryEmptyPreview() {
    StoriumTheme {
        TextFieldPrimary(
            value = "",
            onValueChange = {},
            label = "Email",
        )
    }
}

@Preview
@Composable
private fun TextFieldPrimaryFilledPreview() {
    StoriumTheme {
        TextFieldPrimary(
            value = "john@example.com",
            onValueChange = {},
            label = "Email",
        )
    }
}

@Preview
@Composable
private fun TextFieldPrimaryErrorPreview() {
    StoriumTheme {
        TextFieldPrimary(
            value = "john@ex",
            onValueChange = {},
            label = "Email",
            isError = true,
            errorText = "Not a valid email address",
        )
    }
}
