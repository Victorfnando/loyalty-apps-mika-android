/*
 *
 * Created by Andreas on 1/27/21 1:22 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 11:07 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.presentation.upload.screen

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class UploadInvoiceActivity : BaseActivity() {
    override fun fragment(): BaseFragment = UploadInvoiceFragment.newInstance(
        intent.getStringExtra(EXTRA_IMAGE_URI).orEmpty()
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        const val REQUEST_CODE_SUCCESS_UPLOAD = 121
        private const val EXTRA_IMAGE_URI = "EXTRA_IMAGE_URI"

        fun callingIntent(context: Context, imageUri: String): Intent {
            return Intent(context, UploadInvoiceActivity::class.java).also {
                it.putExtra(EXTRA_IMAGE_URI, imageUri)
            }
        }
    }
}
