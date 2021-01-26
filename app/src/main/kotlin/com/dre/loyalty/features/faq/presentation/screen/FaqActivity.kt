/*
 * Created by Andreas Oen on 1/13/21 7:09 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/13/21 7:09 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.faq.presentation.screen

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class FaqActivity : BaseActivity() {

    override fun fragment(): BaseFragment = FaqFragment.newInstance()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, FaqActivity::class.java)
    }
}
