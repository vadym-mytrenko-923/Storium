package com.storium.ui.core.alert

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.storium.ui.core.alert.model.AppAlertParams
import com.storium.ui.core.alert.model.AppAlertType
import com.storium.ui.core.modifier.modifyIf
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.alertShapeDefault
import com.storium.ui.theme.appAlertSwipeThresholdSize
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimary1_5X
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.marginPrimary3X
import com.storium.ui.theme.marginPrimaryHalf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.errorGenericOops
import storium.shared.generated.resources.successGenericTitle
import kotlin.math.roundToInt

class AppAlertController internal constructor(
    private val hostState: SnackbarHostState,
    private val scope: CoroutineScope,
) {
    fun showAlert(
        title: String? = null,
        message: String? = null,
        type: AppAlertType = AppAlertType.Error,
    ) {
        scope.launch {
            hostState.showSnackbar(
                AppAlertParams(
                    message = "",
                    title = title,
                    description = message,
                    type = type,
                )
            )
        }
    }
}

val LocalAppAlert = staticCompositionLocalOf<AppAlertController> {
    error("No AppAlertController provided")
}

@Composable
fun AppAlertProvider(content: @Composable () -> Unit) {
    val alertHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val alertController = remember(alertHostState, scope) {
        AppAlertController(alertHostState, scope)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
    ) {
        CompositionLocalProvider(LocalAppAlert provides alertController) {
            content()
        }

        SnackbarHost(
            hostState = alertHostState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .systemBarsPadding(),
            snackbar = { data ->
                val alertParams = (data.visuals as? AppAlertParams) ?: return@SnackbarHost
                val title = alertParams.title ?: when (alertParams.type) {
                    AppAlertType.Error -> stringResource(Res.string.errorGenericOops)
                    AppAlertType.Success -> stringResource(Res.string.successGenericTitle)
                }
                val dismissThresholdPx = with(density) { appAlertSwipeThresholdSize.toPx() }
                val offsetY = remember(data) { Animatable(0f) }

                AppAlertBanner(
                    title = title,
                    description = alertParams.description,
                    type = alertParams.type,
                    modifier = Modifier
                        .offset { IntOffset(0, offsetY.value.roundToInt()) }
                        .draggable(
                            orientation = Orientation.Vertical,
                            state = rememberDraggableState { delta ->
                                val newValue = (offsetY.value + delta).coerceAtMost(0f)
                                scope.launch { offsetY.snapTo(newValue) }
                            },
                            onDragStopped = {
                                scope.launch {
                                    if (offsetY.value <= -dismissThresholdPx) {
                                        data.dismiss()
                                    } else {
                                        offsetY.animateTo(
                                            targetValue = 0f,
                                            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                                        )
                                    }
                                }
                            },
                        ),
                )
            },
        )
    }
}

@Composable
private fun AppAlertBanner(
    title: String,
    description: String?,
    type: AppAlertType,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = marginPrimary3X)
            .background(
                color = when (type) {
                    AppAlertType.Error -> MaterialTheme.appColors.error
                    AppAlertType.Success -> MaterialTheme.appColors.backgroundSuccess
                },
                shape = alertShapeDefault,
            )
            .modifyIf(type == AppAlertType.Success) {
                background(
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = alertShapeDefault,
                )
            }
            .padding(horizontal = marginPrimary3X, vertical = marginPrimary2X),
        horizontalArrangement = Arrangement.spacedBy(marginPrimary1_5X),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(marginPrimaryHalf),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.appColors.textPrimaryOnDark,
            )

            description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.appColors.textPrimaryOnDark,
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppAlertBannerErrorPreview() {
    StoriumTheme {
        AppAlertBanner(
            title = "Oops!",
            description = "Invalid username or password",
            type = AppAlertType.Error,
        )
    }
}

@Preview
@Composable
private fun AppAlertBannerErrorTitleOnlyPreview() {
    StoriumTheme {
        AppAlertBanner(
            title = "Something went wrong",
            description = null,
            type = AppAlertType.Error,
        )
    }
}

@Preview
@Composable
private fun AppAlertBannerSuccessPreview() {
    StoriumTheme {
        AppAlertBanner(
            title = "Success",
            description = "You are now logged in",
            type = AppAlertType.Success,
        )
    }
}
