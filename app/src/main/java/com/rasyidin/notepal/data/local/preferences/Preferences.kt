package com.rasyidin.notepal.data.local.preferences

interface Preferences {
    fun clearPreferences()
    fun setOnBoardingSession(onBoarded: Boolean)


    fun getOnBoardingSession(): Boolean

    companion object {
        const val PREF_NAME = "note_pal_pref"
        const val KEY_ON_BOARDING_SESSION = "onBoardingSession"
        const val KEY_CLEAR = "clear"
    }
}