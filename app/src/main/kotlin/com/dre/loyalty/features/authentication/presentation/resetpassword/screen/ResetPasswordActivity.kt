/*
 *
 * Created by Andreas on 1/27/21 1:20 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/24/21 3:31 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.presentation.resetpassword.screen

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class ResetPasswordActivity : BaseActivity() {

    override fun fragment(): BaseFragment = ResetPasswordFragment.newInstance()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, ResetPasswordActivity::class.java)
    }
}
