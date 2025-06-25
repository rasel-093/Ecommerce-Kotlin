package com.rasel.ecommerce_kotlin

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Sets the flag to remove layout limits, allowing the app content to extend beyond the status and navigation bars.
// This enables a full-screen experience where the layout is not constrained by system UI boundaries.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        // Clears the translucent status bar flag, removing any transparency effect from the status bar.
        // This ensures the status bar appears solid and integrates with the app's layout.
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        // Adds the flag to draw system bar backgrounds, allowing the app to render content behind the status bar.
        // This enables customization of the status bar appearance with the app's background.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        // Sets the system UI visibility to light status bar, making status bar icons dark for better contrast.
        // This is typically used with a light background to ensure readability of status bar elements.
        //Hide  third party app notification icon.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

    }
}