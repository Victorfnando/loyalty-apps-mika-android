/*
 * Created by Andreas Oen on 12/27/20 10:47 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/26/20 7:51 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.login.presentation.ui

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity

class LoginActivity : BaseActivity() {

    override fun fragment() = LoginFragment.newInstance()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
