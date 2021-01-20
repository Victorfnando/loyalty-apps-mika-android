/*
 * Created by Andreas Oen on 1/20/21 6:32 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 6:32 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.passwordinput.presentation.screen

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.features.passwordinput.presentation.enumtype.InputPasswordType

class InputPasswordActivity : BaseActivity() {

    override fun fragment(): BaseFragment = InputPasswordFragment.newInstance(
        intent.extras?.get(EXTRA_PASSWORD_INPUT_TYPE) as InputPasswordType
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        private const val EXTRA_PASSWORD_INPUT_TYPE = "EXTRA_PASSWORD_INPUT_TYPE"

        fun callingIntent(context: Context, passwordType: InputPasswordType): Intent {
            return Intent(context, InputPasswordActivity::class.java).also {
                it.putExtra(EXTRA_PASSWORD_INPUT_TYPE, passwordType)
            }
        }
    }
}
