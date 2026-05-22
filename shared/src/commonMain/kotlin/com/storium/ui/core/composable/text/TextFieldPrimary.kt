package com.storium.ui.core.composable.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.elevationInput
import com.storium.ui.theme.inputHeight
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.marginPrimary2_5X
import com.storium.ui.theme.marginPrimaryHalf
import com.storium.ui.theme.textFieldShapeDefault
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPrimary(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    isValid: Boolean = false,
    errorText: String? = null,
    isEnabled: Boolean = true,
    label: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val showLabelMinimized = value.isNotEmpty() || isFocused
    val displayTrailingIcon = trailingIcon ?: when {
        isError -> {
            {
                Image(
                    painter = painterResource(AppIcons.ErrorClose),
                    contentDescription = null,
                    modifier = Modifier.size(defaultIconSize),
                )
            }
        }

        isValid -> {
            {
                Image(
                    painter = painterResource(AppIcons.InputCheck),
                    contentDescription = null,
                    modifier = Modifier.size(defaultIconSize),
                )
            }
        }

        else -> null
    }

    val colors = TextFieldDefaults.colors(
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
    )

    Column(modifier = modifier) {
        BasicTextField(
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
            interactionSource = interactionSource,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            textStyle = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.appColors.textInput,
            ),
            cursorBrush = SolidColor(MaterialTheme.appColors.primary),
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    enabled = isEnabled,
                    singleLine = true,
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    isError = isError,
                    label = label.takeIf { it.isNotEmpty() }?.let {
                        {
                            Text(
                                text = label,
                                style = if (showLabelMinimized) {
                                    MaterialTheme.typography.bodySmall
                                } else {
                                    MaterialTheme.typography.labelLarge
                                },
                                color = if (isError) {
                                    MaterialTheme.appColors.error
                                } else {
                                    MaterialTheme.appColors.textSecondary
                                },
                            )
                        }
                    },
                    leadingIcon = leadingIcon,
                    trailingIcon = displayTrailingIcon,
                    shape = textFieldShapeDefault,
                    colors = colors,
                    contentPadding = PaddingValues(
                        start = marginPrimary2_5X,
                        end = marginPrimary2_5X,
                        top = marginPrimary2X,
                        bottom = marginPrimary2X,
                    ),
                )
            },
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
            label = "Username",
        )
    }
}

@Preview
@Composable
private fun TextFieldPrimaryValidPreview() {
    StoriumTheme {
        TextFieldPrimary(
            value = "emilys",
            onValueChange = {},
            label = "Username",
            isValid = true,
        )
    }
}

@Preview
@Composable
private fun TextFieldPrimaryErrorPreview() {
    StoriumTheme {
        TextFieldPrimary(
            value = "",
            onValueChange = {},
            label = "Username",
            isError = true,
            errorText = "Username should not be empty",
        )
    }
}
