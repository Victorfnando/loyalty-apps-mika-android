/*
 *  Created by Andreas Oentoro on 12/20/20 2:50 PM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 12/20/20 2:50 PM
 *  Github Profile: https://github.com/oandrz
 */

package com.dre.loyalty.features.authenticationselector.presentation

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class AuthenticationSelectorActivity : BaseActivity() {

    override fun fragment(): BaseFragment = AuthenticationSelectorFragment.newInstance()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, AuthenticationSelectorActivity::class.java)
    }
}
