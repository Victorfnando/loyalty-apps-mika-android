/*
 * Created by Andreas Oen on 12/26/20 4:31 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/26/20 4:31 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.resetpassword.presentation

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
