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
import com.dre.loyalty.features.invoice.presentation.entity.UploadInvoiceState

class EWalletActivity : BaseActivity() {

    override fun fragment(): BaseFragment = EWalletFragment.newInstance(
        intent.extras?.getParcelable<UploadInvoiceState>(EXTRA_UPLOAD_INVOICE_STATE) as UploadInvoiceState
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        const val REQUEST_CODE_UPLOAD = 102
        private const val EXTRA_UPLOAD_INVOICE_STATE = "EXTRA_UPLOAD_INVOICE_STATE"

        fun callingIntent(context: Context, state: UploadInvoiceState) : Intent {
            return Intent(context, EWalletActivity::class.java).also {
                it.putExtra(EXTRA_UPLOAD_INVOICE_STATE, state)
            }
        }
    }
}
