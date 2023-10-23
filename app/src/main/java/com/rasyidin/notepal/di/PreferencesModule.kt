package com.rasyidin.notepal.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.rasyidin.notepal.data.local.preferences.NotePalPreferences
import com.rasyidin.notepal.data.local.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(
        app: Application
    ): SharedPreferences = app.getSharedPreferences(Preferences.PREF_NAME, MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesPreferences(sharedPref: SharedPreferences): Preferences = NotePalPreferences(sharedPref)
}