package com.rasel.ecommerce_kotlin
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rasel.ecommerce_kotlin.model.ItemModel
import com.rasel.ecommerce_kotlin.screens.IntroScreen
import com.rasel.ecommerce_kotlin.screens.mainscreen.ExplorerScreen
import com.rasel.ecommerce_kotlin.screens.navigations.BottomNavBar
import com.rasel.ecommerce_kotlin.screens.productdetails.ProductDetailsScreen
import com.rasel.ecommerce_kotlin.utils.currentRoute

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EcommerceApp(navController: NavHostController) {
    val currentRoute = currentRoute(navController)
    Scaffold(
        bottomBar = {
            if (currentRoute!="intro"){
                BottomNavBar(
                    selectedRoute = currentRoute ?: "home",
                    onItemClick = { route->
                        if (route != currentRoute) {
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) {padding->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController, startDestination = "intro"
        ) {
            composable("intro") {
                //HomeScreen()
                IntroScreen(onClick = {navController.navigate("explorer")})
            }
            composable("explorer") {
                ExplorerScreen(navController)
            }
            composable("cart") {
                // CartScreen()
            }
            composable("favorite") {
                //FavoriteScreen()
            }
            composable(
                "orders"
            ) {
                //OrderScreen()
            }
            composable("profile") {
                //ProfileScreen()
            }

            composable("checkout") {
                //Checkout
            }
            composable(
                "productdetails"
            ) {
                ProductDetailsScreen(
                    ItemModel(),{}, {}, {}
                )
            }
        }
    }
}