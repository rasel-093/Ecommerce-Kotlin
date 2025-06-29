package com.rasel.ecommerce_kotlin.screens.navigations

import androidx.annotation.DrawableRes
import com.rasel.ecommerce_kotlin.R

sealed class BottomNavRoute(
    val route: String,
    @DrawableRes val icon: Int,
    val title: String
){
    object Home: BottomNavRoute("explorer", R.drawable.btn_1, "Explorer")
    object Cart: BottomNavRoute("cart", R.drawable.btn_2, "Cart")
    object Favorite: BottomNavRoute("favorite", R.drawable.btn_3, "Favorite")
    object Orders: BottomNavRoute("orders", R.drawable.btn_4, "Orders")
    object Profile: BottomNavRoute("profile", R.drawable.btn_5, "Profile")

    companion object{
        val items = listOf(Home, Cart, Favorite, Orders, Profile)
    }
}