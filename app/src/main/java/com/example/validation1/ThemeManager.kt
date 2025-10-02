package com.example.validation1

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object ThemeManager {
    private const val PREFS_NAME = "theme_prefs"
    private const val KEY_DARK_MODE = "dark_mode"

    private var _isDarkMode: MutableState<Boolean> = mutableStateOf(false)
    val isDarkMode: Boolean
        get() = _isDarkMode.value

    fun initialize(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        _isDarkMode.value = prefs.getBoolean(KEY_DARK_MODE, false)
    }

    fun toggleTheme(context: Context) {
        _isDarkMode.value = !_isDarkMode.value
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_DARK_MODE, _isDarkMode.value).apply()
    }

    fun isDarkModeState(): MutableState<Boolean> = _isDarkMode
}