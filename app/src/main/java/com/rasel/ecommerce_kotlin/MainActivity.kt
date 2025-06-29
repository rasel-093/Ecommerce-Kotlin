package com.rasel.ecommerce_kotlin

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.rasel.ecommerce_kotlin.ui.theme.EcommerceKotlinTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceKotlinTheme {
                val navController = rememberNavController()
                EcommerceApp(navController)
            }
        }
    }
}