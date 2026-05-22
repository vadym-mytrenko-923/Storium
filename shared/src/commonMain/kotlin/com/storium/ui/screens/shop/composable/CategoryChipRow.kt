package com.storium.ui.screens.shop.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.screens.shop.composable.preview.ShopPreviewUiModels
import com.storium.ui.screens.shop.model.CategoryUiModel
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.chipShapeDefault
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary1_5X
import com.storium.ui.theme.marginPrimary2X

@Composable
fun CategoryChipRow(
    modifier: Modifier = Modifier,
    categories: List<CategoryUiModel>,
    onCategoryClicked: (CategoryUiModel) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = marginPrimary2X),
        horizontalArrangement = Arrangement.spacedBy(marginPrimary),
    ) {
        items(categories, key = { it.id }) { category ->
            CategoryChip(
                text = category.name,
                isSelected = category.isSelected,
                onClick = { onCategoryClicked(category) },
            )
        }
    }
}

@Composable
private fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(chipShapeDefault)
            .background(
                if (isSelected) {
                    MaterialTheme.appColors.categoryChipBackground
                } else {
                    MaterialTheme.appColors.background
                },
            )
            .clickable { onClick() }
            .padding(marginPrimary1_5X),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = if (isSelected) {
                MaterialTheme.appColors.categoryChipText
            } else {
                MaterialTheme.appColors.textPrimary
            },
        )
    }
}

@Preview
@Composable
private fun CategoryChipRowPreview() {
    StoriumTheme {
        CategoryChipRow(
            categories = ShopPreviewUiModels.categories,
            onCategoryClicked = {},
        )
    }
}
