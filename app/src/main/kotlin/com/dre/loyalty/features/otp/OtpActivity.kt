/*
 *  Created by Andreas Oentoro on 12/20/20 4:56 PM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 12/20/20 4:56 PM
 *  Github Profile: https://github.com/oandrz
 */

package com.dre.loyalty.features.otp

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class OtpActivity : BaseActivity() {

    override fun fragment(): BaseFragment = OtpFragment.newInstance()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, OtpActivity::class.java)
    }
}
