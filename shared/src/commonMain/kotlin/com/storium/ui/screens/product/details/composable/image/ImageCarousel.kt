package com.storium.ui.screens.product.details.composable.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import coil3.compose.AsyncImage
import com.storium.ui.screens.shop.composable.common.DiscountChip
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.carouselImageHeight
import com.storium.ui.theme.carouselIndicatorHeight
import com.storium.ui.theme.carouselIndicatorShape
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimaryHalf

private const val PAGE_SIZE_FRACTION = 0.7f

@Composable
fun ImageCarousel(
    modifier: Modifier = Modifier,
    images: List<String>,
    discountPercent: Int? = null,
) {
    if (images.isEmpty()) return

    val hasMultipleImages = images.size > 1
    val pagerState = rememberPagerState { images.size }
    val pageSize = remember(hasMultipleImages) {
        if (hasMultipleImages) {
            object : PageSize {
                override fun Density.calculateMainAxisPageSize(
                    availableSpace: Int,
                    pageSpacing: Int,
                ): Int = (availableSpace * PAGE_SIZE_FRACTION).toInt()
            }
        } else {
            PageSize.Fill
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Box {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(carouselImageHeight),
                pageSize = pageSize,
                pageSpacing = marginPrimaryHalf,
            ) { page ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.appColors.surface),
                    model = images.getOrNull(page),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
            }

            discountPercent?.let {
                DiscountChip(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(marginPrimary),
                    discountPercent = it,
                )
            }
        }

        if (hasMultipleImages) {
            CarouselIndicator(pagerState = pagerState)
        }
    }
}

@Composable
private fun CarouselIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val indicatorWidth = maxWidth / pagerState.pageCount
        val indicatorWidthPx = with(LocalDensity.current) { indicatorWidth.toPx() }

        Box(
            modifier = Modifier
                .width(indicatorWidth)
                .height(carouselIndicatorHeight)
                .offset {
                    val progress = pagerState.currentPage + pagerState.currentPageOffsetFraction
                    IntOffset(x = (indicatorWidthPx * progress).toInt(), y = 0)
                }
                .clip(carouselIndicatorShape)
                .background(MaterialTheme.appColors.textPrimary),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageCarouselPreview() {
    StoriumTheme {
        ImageCarousel(
            images = listOf("", "", ""),
            discountPercent = 20,
        )
    }
}
