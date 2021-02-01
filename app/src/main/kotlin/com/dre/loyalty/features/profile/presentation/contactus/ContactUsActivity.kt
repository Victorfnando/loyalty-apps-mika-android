/*
 * Created by Andreas Oen on 1/14/21 8:15 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/14/21 8:15 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.profile.presentation.contactus

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class ContactUsActivity : BaseActivity() {

    override fun fragment(): BaseFragment = ContactUsFragment.newInstance()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, ContactUsActivity::class.java)
    }
}
