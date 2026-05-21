package com.storium.ui.screens.main.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import com.storium.ui.theme.containerShapeDefault
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.screens.main.model.MainTab
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.bottomNavIconSize
import com.storium.ui.theme.bottomNavShapeDefault
import androidx.compose.ui.draw.clip
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.elevationCard
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimaryHalf
import com.storium.ui.theme.marginPrimaryQuarter
import com.storium.ui.theme.textSizeSmall
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainBottomBar(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = bottomNavShapeDefault,
        shadowElevation = elevationCard,
        color = MaterialTheme.appColors.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(top = marginPrimary),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MainTab.entries.forEach { tab ->
                val isSelected = tab == selectedTab

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(containerShapeDefault)
                        .selectable(
                            role = Role.Tab,
                            selected = isSelected,
                            onClick = { onTabSelected(tab) },
                        )
                        .padding(horizontal = marginPrimary2X, vertical = marginPrimaryHalf),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(marginPrimaryQuarter),
                ) {
                    Image(
                        painter = painterResource(if (isSelected) tab.activeIcon else tab.inactiveIcon),
                        contentDescription = stringResource(tab.labelRes),
                        modifier = Modifier.size(bottomNavIconSize),
                    )

                    Text(
                        text = stringResource(tab.labelRes),
                        fontSize = textSizeSmall,
                        color = if (isSelected) {
                            MaterialTheme.appColors.primary
                        } else {
                            MaterialTheme.appColors.textSecondary
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainBottomBarShopSelectedPreview() {
    StoriumTheme {
        MainBottomBar(
            selectedTab = MainTab.Shop,
            onTabSelected = {},
        )
    }
}

@Preview
@Composable
private fun MainBottomBarProfileSelectedPreview() {
    StoriumTheme {
        MainBottomBar(
            selectedTab = MainTab.Profile,
            onTabSelected = {},
        )
    }
}
