package com.rasyidin.notepal.data.local.preferences

import android.content.SharedPreferences

class NotePalPreferences(
    private val sharedPref: SharedPreferences
) : Preferences {
    override fun clearPreferences() {
        sharedPref.edit().clear().apply()
    }

    override fun setOnBoardingSession(onBoarded: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.KEY_ON_BOARDING_SESSION, onBoarded)
            .apply()
    }

    override fun getOnBoardingSession(): Boolean {
        return sharedPref.getBoolean(Preferences.KEY_ON_BOARDING_SESSION, false)
    }
}