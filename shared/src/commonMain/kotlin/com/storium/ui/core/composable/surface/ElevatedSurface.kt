package com.storium.ui.core.composable.surface

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.elevationCard
import com.storium.ui.theme.marginPrimary2X

@Composable
fun ElevatedSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    shadowElevation: Dp = elevationCard,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        shadowElevation = shadowElevation,
        color = MaterialTheme.appColors.cardBackground,
        content = content,
    )
}

@Preview(showBackground = true)
@Composable
private fun ElevatedSurfacePreview() {
    StoriumTheme {
        ElevatedSurface(
            modifier = Modifier.padding(marginPrimary2X),
        ) {
            Text(
                text = "Elevated Surface",
                modifier = Modifier.padding(marginPrimary2X),
            )
        }
    }
}
