package com.rasel.ecommerce_kotlin.screens.mainscreen.banner

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rasel.ecommerce_kotlin.R

@Composable
fun DotIndicator(
    modifier: Modifier = Modifier,
    indicatorCount: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(R.color.purple),
    unSelectedColor: Color = colorResource(R.color.grey),
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(indicatorCount){index->
            IndicatorDot(
                size = 15.dp,
                color = if (index == selectedIndex) selectedColor else unSelectedColor
            )
            if (index != indicatorCount-1){
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}