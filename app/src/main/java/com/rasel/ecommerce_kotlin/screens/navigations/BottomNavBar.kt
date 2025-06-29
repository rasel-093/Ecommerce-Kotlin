package com.rasel.ecommerce_kotlin.screens.navigations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rasel.ecommerce_kotlin.R

@Composable
fun BottomNavBar(
    selectedRoute: String,
    onItemClick: (String)->Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
            .background(
                color = colorResource(R.color.purple),
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val navItems = BottomNavRoute.items
        navItems.forEach {
            BottomNavBarItem(
                icon = painterResource(it.icon),
                text = it.title,
                onItemClick = { onItemClick(it.route) },
                selected = selectedRoute == it.route
            )
        }
    }
}