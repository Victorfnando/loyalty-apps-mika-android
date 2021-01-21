/*
 * Created by Andreas Oen on 1/21/21 4:32 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 4:20 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.ewallet.presentation.screen

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class EWalletActivity : BaseActivity() {

    override fun fragment(): BaseFragment = EWalletFragment.newInstance()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context) : Intent {
            return Intent(context, EWalletActivity::class.java)
        }
    }
}
