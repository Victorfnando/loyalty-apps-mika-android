/*
 *
 * Created by Andreas on 1/28/21 3:57 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 3:57 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.util.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthenticationManager @Inject constructor(context: Context) {
    private val sp: SharedPreferences = context.getSharedPreferences(
        AUTH_DATA_STORE_NAME,
        Context.MODE_PRIVATE
    )

    fun saveUserId(userId: String) {
        with(sp.edit()) {
            putString(USER_ID, userId)
            apply()
        }
    }

    fun saveToken(token: String) {
        with(sp.edit()) {
            putString(TOKEN, token)
            apply()
        }
    }

    fun getUserId(): String? {
        return sp.getString(USER_ID, null)
    }

    fun getToken(): String? {
        return sp.getString(TOKEN, null)
    }

    companion object {
        private const val AUTH_DATA_STORE_NAME = "AUTH_DATA_STORE"
        private const val USER_ID = "USER_ID_KEY"
        private const val TOKEN = "TOKEN"
    }
}