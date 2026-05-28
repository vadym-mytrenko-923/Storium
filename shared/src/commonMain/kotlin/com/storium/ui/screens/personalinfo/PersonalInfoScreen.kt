package com.storium.ui.screens.personalinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.storium.ui.core.alert.LocalAppAlert
import com.storium.ui.core.alert.model.AppAlertType
import com.storium.ui.core.composable.toolbar.Toolbar
import com.storium.ui.screens.personalinfo.composable.PersonalInfoContent
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary2X
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.personalInfoSaveSuccess
import storium.shared.generated.resources.personalInfoTitle

@Composable
fun PersonalInfoScreen(viewModel: PersonalInfoViewModel = koinViewModel()) {
    val appAlert = LocalAppAlert.current
    val state by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.uiEffectFlow.collectLatest { effect ->
            when (effect) {
                is PersonalInfoEffect.UpdatedSuccessfully -> appAlert.showAlert(
                    message = getString(Res.string.personalInfoSaveSuccess),
                    type = AppAlertType.Success,
                )
            }
        }
    }

    PersonalInfoScreenContent(
        state = state,
        onIntent = viewModel::onUserIntent,
    )
}

@Composable
private fun PersonalInfoScreenContent(
    modifier: Modifier = Modifier,
    state: PersonalInfoScreenState,
    onIntent: (PersonalInfoIntent) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.appColors.background,
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            verticalArrangement = Arrangement.spacedBy(marginPrimary),
        ) {
            Toolbar(
                title = stringResource(Res.string.personalInfoTitle),
                topPadding = paddingValues.calculateTopPadding() + marginPrimary2X,
                leadingContent = {
                    Image(
                        modifier = Modifier
                            .size(defaultIconSize)
                            .clickable { onIntent(PersonalInfoIntent.BackClicked) },
                        painter = painterResource(AppIcons.ChevronLeft),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.appColors.iconPrimary),
                    )
                },
            )

            PersonalInfoContent(
                modifier = Modifier.weight(1f),
                state = state,
                onIntent = onIntent,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonalInfoScreenContentPreview() {
    StoriumTheme {
        PersonalInfoScreenContent(
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
