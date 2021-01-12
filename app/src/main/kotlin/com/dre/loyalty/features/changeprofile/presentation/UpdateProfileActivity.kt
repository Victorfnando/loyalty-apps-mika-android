/*
 * Created by Andreas Oen on 1/11/21 6:45 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/11/21 6:45 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.changeprofile.presentation

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class UpdateProfileActivity : BaseActivity() {

    override fun fragment(): BaseFragment = UpdateProfileFragment.newInstance()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, UpdateProfileActivity::class.java)
    }
}
