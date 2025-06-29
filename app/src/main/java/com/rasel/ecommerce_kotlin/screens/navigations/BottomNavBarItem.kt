package com.rasel.ecommerce_kotlin.screens.navigations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rasel.ecommerce_kotlin.R

@Composable
fun BottomNavBarItem(
    icon: Painter,
    text: String,
    onItemClick: (() -> Unit)? = null,
    selected: Boolean
) {
    val bg = if (selected) colorResource(R.color.purple_200) else colorResource(R.color.purple)
    Column(
        modifier = Modifier
            .background(color = bg, shape = RoundedCornerShape(8.dp))
            .height(60.dp)
            .clickable { onItemClick?.invoke() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = text,
            tint = Color.White
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = 10.sp
        )
    }
}