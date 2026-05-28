package com.storium.ui.screens.personalinfo.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.core.composable.button.BtnPrimary
import com.storium.ui.core.composable.text.TextFieldPrimary
import com.storium.ui.screens.personalinfo.PersonalInfoIntent
import com.storium.ui.screens.personalinfo.PersonalInfoScreenState
import com.storium.ui.screens.personalinfo.validation.PersonalInfoValidationResult
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.marginPrimary2X
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.personalInfoBtnSave
import storium.shared.generated.resources.personalInfoEmailError
import storium.shared.generated.resources.personalInfoEmailLabel
import storium.shared.generated.resources.personalInfoFirstNameError
import storium.shared.generated.resources.personalInfoFirstNameLabel
import storium.shared.generated.resources.personalInfoLastNameError
import storium.shared.generated.resources.personalInfoLastNameLabel

@Composable
fun PersonalInfoContent(
    modifier: Modifier = Modifier,
    state: PersonalInfoScreenState,
    onIntent: (PersonalInfoIntent) -> Unit,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = marginPrimary2X),
            verticalArrangement = Arrangement.spacedBy(marginPrimary2X),
        ) {
            TextFieldPrimary(
                value = state.firstName,
                onValueChange = { onIntent(PersonalInfoIntent.FirstNameChanged(it)) },
                label = stringResource(Res.string.personalInfoFirstNameLabel),
                isError = state.validation.showFirstNameError,
                errorText = stringResource(Res.string.personalInfoFirstNameError),
            )

            TextFieldPrimary(
                value = state.lastName,
                onValueChange = { onIntent(PersonalInfoIntent.LastNameChanged(it)) },
                label = stringResource(Res.string.personalInfoLastNameLabel),
                isError = state.validation.showLastNameError,
                errorText = stringResource(Res.string.personalInfoLastNameError),
            )

            TextFieldPrimary(
                value = state.email,
                onValueChange = { onIntent(PersonalInfoIntent.EmailChanged(it)) },
                label = stringResource(Res.string.personalInfoEmailLabel),
                isError = state.validation.showEmailError,
                errorText = stringResource(Res.string.personalInfoEmailError),
            )
        }

        BtnPrimary(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = marginPrimary2X)
                .padding(bottom = marginPrimary2X),
            text = stringResource(Res.string.personalInfoBtnSave),
            isEnabled = state.isSaveButtonEnabled,
            onClick = { onIntent(PersonalInfoIntent.SaveClicked) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonalInfoContentPreview() {
    StoriumTheme {
        PersonalInfoContent(
            state = PersonalInfoScreenState(
                firstName = "Matilda",
                lastName = "Brown",
                email = "matildabrown@mail.com",
                initialFirstName = "Matilda",
                initialLastName = "Brown",
                initialEmail = "matildabrown@mail.com",
            ),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonalInfoContentChangedPreview() {
    StoriumTheme {
        PersonalInfoContent(
            state = PersonalInfoScreenState(
                firstName = "Matilda",
                lastName = "Smith",
                email = "matildabrown@mail.com",
                initialFirstName = "Matilda",
                initialLastName = "Brown",
                initialEmail = "matildabrown@mail.com",
            ),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonalInfoContentErrorPreview() {
    StoriumTheme {
        PersonalInfoContent(
            state = PersonalInfoScreenState(
                firstName = "",
                lastName = "Brown",
                email = "",
                initialFirstName = "Matilda",
                initialLastName = "Brown",
                initialEmail = "matildabrown@mail.com",
                validation = PersonalInfoValidationResult(
                    isFirstNameValid = false,
                    isEmailValid = false,
                    showFirstNameError = true,
                    showEmailError = true,
                ),
            ),
            onIntent = {},
        )
    }
}
