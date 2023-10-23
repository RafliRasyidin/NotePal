package com.rasyidin.notepal.ui.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.rasyidin.notepal.MainActivity
import com.rasyidin.notepal.data.local.preferences.Preferences
import com.rasyidin.notepal.ui.screen.on_boarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    @Inject lateinit var pref: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        val isOnBoarded = pref.getOnBoardingSession()
        lifecycleScope.launch(Dispatchers.Main) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                delay(1000)
            }
            val intent: Intent = if (isOnBoarded) {
                Intent(this@SplashActivity, MainActivity::class.java)
            } else Intent(this@SplashActivity, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}