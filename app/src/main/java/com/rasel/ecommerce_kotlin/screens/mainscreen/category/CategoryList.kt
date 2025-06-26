package com.rasel.ecommerce_kotlin.screens.mainscreen.category

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rasel.ecommerce_kotlin.model.CategoryModel

@Composable
fun CategoryList(categories: SnapshotStateList<CategoryModel>) {
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        items(categories.size){index: Int ->
            CategoryItem(
                item = categories[index],
                isSelected = index == selectedIndex,
                onItemClick = {
                    selectedIndex = index
                }
            )
        }
    }
}