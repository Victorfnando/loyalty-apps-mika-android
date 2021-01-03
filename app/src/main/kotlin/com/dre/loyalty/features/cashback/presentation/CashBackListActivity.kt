/*
 * Created by Andreas Oen on 1/3/21 2:16 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/3/21 2:16 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.cashback.presentation

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class CashBackListActivity : BaseActivity() {

    override fun fragment(): BaseFragment = CashBackListFragment.newInstance()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, CashBackListActivity::class.java)
        }
    }
}
