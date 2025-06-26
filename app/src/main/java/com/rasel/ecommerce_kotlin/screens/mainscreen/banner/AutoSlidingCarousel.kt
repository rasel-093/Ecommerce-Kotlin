package com.rasel.ecommerce_kotlin.screens.mainscreen.banner

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.rasel.ecommerce_kotlin.model.SliderModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    pagerState: PagerState = remember { PagerState() },
    banners: List<SliderModel>
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(count = banners.size, state = pagerState) {
                page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(banners[page].url)
                    .build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                    .height(150.dp)
            )
        }
        DotIndicator(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            indicatorCount = banners.size,
            selectedIndex = pagerState.currentPage,
            dotSize = 8.dp
        )
    }
}