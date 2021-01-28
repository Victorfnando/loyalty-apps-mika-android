/*
 *
 * Created by Andreas on 1/27/21 1:47 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 11:07 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.presentation.otp.screen

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class OtpActivity : BaseActivity() {

    override fun fragment(): BaseFragment = OtpFragment.newInstance(
        intent.extras?.getString(EXTRA_EMAIL).orEmpty()
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        private const val EXTRA_EMAIL = "EXTRA_EMAIL"

        fun callingIntent(context: Context, email: String): Intent =
            Intent(context, OtpActivity::class.java).also {
                it.putExtra(EXTRA_EMAIL, email)
            }
    }
}
