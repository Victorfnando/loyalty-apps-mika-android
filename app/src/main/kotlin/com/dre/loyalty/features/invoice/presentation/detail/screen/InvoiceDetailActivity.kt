/*
 * Created by Andreas Oen on 1/18/21 4:08 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 4:08 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.detail.screen

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class InvoiceDetailActivity : BaseActivity() {

    override fun fragment(): BaseFragment = InvoiceDetailFragment.newInstance(
        intent.extras?.getString(EXTRA_RECEIPT_ID).orEmpty()
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        private const val EXTRA_RECEIPT_ID = "EXTRA_RECEIPT_ID"

        fun callingIntent(context: Context, id: String): Intent {
            return Intent(context, InvoiceDetailActivity::class.java).also {
                it.putExtra(EXTRA_RECEIPT_ID, id)
            }
        }
    }
}
