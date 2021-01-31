/*
 *
 * Created by Andreas on 1/27/21 1:29 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 11:07 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.presentation.changeprofile.screen

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.platform.BaseActivity
import com.dre.loyalty.core.platform.BaseFragment

class UpdateProfileActivity : BaseActivity() {

    override fun fragment(): BaseFragment = UpdateProfileFragment.newInstance(
        intent.extras?.getParcelable<User>(EXTRA_USER) as User
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    companion object {
        private const val EXTRA_USER = "EXTRA_USER"

        fun callingIntent(context: Context, user: User) =
            Intent(context, UpdateProfileActivity::class.java).also {
                it.putExtra(EXTRA_USER, user)
            }
    }
}
