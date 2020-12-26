/*
 * Created by Andreas Oen on 12/26/20 4:31 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/26/20 4:31 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.resetpin.presentation

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class ResetPinActivity : BaseActivity(), ResetPinFragment.FragmentListener {

    override fun fragment(): BaseFragment = ResetPinFragment.newInstance()

    override fun handleBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, ResetPinActivity::class.java)
    }
}
