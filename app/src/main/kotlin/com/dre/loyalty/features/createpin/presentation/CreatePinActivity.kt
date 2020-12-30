/*
 * Created by Andreas Oen on 12/29/20 7:08 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/29/20 7:08 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.createpin.presentation

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.features.createpin.presentation.enums.CreatePinType

class CreatePinActivity : BaseActivity() {

    override fun fragment(): BaseFragment = CreatePinFragment.newInstance(
        intent.extras?.get(EXTRA_TYPE) as CreatePinType
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        private const val EXTRA_TYPE = "EXTRA_TYPE"

        fun callingIntent(context: Context, type: CreatePinType): Intent {
            return Intent(context, CreatePinActivity::class.java).also {
                it.putExtra(EXTRA_TYPE, type)
            }
        }
    }
}
