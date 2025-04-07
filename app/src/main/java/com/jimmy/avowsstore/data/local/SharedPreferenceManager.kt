package com.jimmy.avowsstore.data.local

import android.content.Context
import com.jimmy.avowsstore.domain.model.Login

class SharedPreferenceManager(
    context: Context
) {
    companion object {
        private const val PREF_NAME = "AVOWS_STORE"
        private const val KEY_TOKEN = "TOKEN"
        private const val KEY_USER_ID = "USER_ID"
        private const val KEY_USERNAME = "USERNAME"

    }

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
    fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun putToken(token: String) {
        putString(KEY_TOKEN, token)
    }

    fun getToken(): String {
        return getString(KEY_TOKEN)
    }

    fun putUserId(userId: Int) {
        putInt(KEY_USER_ID, userId)
    }

    fun getUserId(): Int {
        return getInt(KEY_USER_ID)
    }

    fun putUsername(username: String) {
        putString(KEY_USERNAME, username)
    }

    fun getUsername(): String {
        return getString(KEY_USERNAME)
    }

    fun logout() {
        putToken("")
        putUsername("")
        putUserId(0)
    }

    fun isLogin(): Boolean {
        return getToken().isNotEmpty()
    }

    fun login(dataLogin: Login) {
        putToken(dataLogin.token)
        putUsername(dataLogin.username)
        putUserId(dataLogin.userId)
    }
}